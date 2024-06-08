package ru.heumn.paymentservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.heumn.paymentservice.storages.dto.CourseDtoResponse;
import ru.heumn.paymentservice.storages.dto.PaymentAcceptDto;
import ru.heumn.paymentservice.storages.entity.CourseEntity;
import ru.heumn.paymentservice.storages.entity.UserEntity;
import ru.heumn.paymentservice.storages.event.PaymentAcceptEvent;
import ru.heumn.paymentservice.storages.event.PaymentReturnEvent;
import ru.heumn.paymentservice.storages.exceptions.BadRequestException;
import ru.heumn.paymentservice.storages.exceptions.NotFoundException;
import ru.heumn.paymentservice.storages.exceptions.RuntimeException;
import ru.heumn.paymentservice.storages.repository.UserRepository;
import ru.heumn.paymentservice.util.HibernateUtil;

import java.math.BigDecimal;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

    UserRepository userRepository = new UserRepository(HibernateUtil.buildSessionFactory());

    private final WebClient.Builder webClientBuilder;

    private final KafkaTemplate<String, PaymentAcceptEvent> kafkaTemplateAccept;

    private final KafkaTemplate<String, PaymentReturnEvent> kafkaTemplateReturn;

    @Override
    public Boolean refillMoneyToUser(Long idUser, Double money) throws NotFoundException {
        log.info(String.format("triggering method: refillMoneyToUser with parameters: %s, %s", idUser, money));
        UserEntity user = userRepository.findById(idUser).orElseThrow(() -> new NotFoundException("User not found"));
        Double balance = user.getMoney() + money;
        user.setMoney(balance);

        try {
            userRepository.update(user);
            return true;
        } catch (Exception e) {
            log.error(String.format("Error in method: refillMoneyToUser with parameters: %s, %s error: %s", idUser, money, e));
            return false;
        }
    }

    @Override
    public PaymentAcceptDto paymentCourse(Long idUser, Long courseId) throws NotFoundException, BadRequestException, RuntimeException {
        log.info(String.format("triggering method: paymentCourse with parameters: %s, %s", idUser, courseId));
        UserEntity user = userRepository.findById(idUser).orElseThrow(() -> new NotFoundException("User not found"));

        Boolean courseHas;
        try {
            courseHas = webClientBuilder.build().get()
                    .uri("http://user-service/api/users/" + idUser + "/courses/" + courseId + "/has")
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            log.info(String.format("webClient users end work: course =  %s", courseHas));
        } catch (Exception e) {
            log.error("webClient user error ");
            throw new RuntimeException("User server error");
        }

        if (Boolean.FALSE.equals(courseHas)) {
            CourseDtoResponse course;
            try {
                course = webClientBuilder.build().get()
                        .uri("http://course-service/api/education/course/" + courseId)
                        .retrieve()
                        .bodyToMono(CourseDtoResponse.class)
                        .block();
                log.info("webClient courses end work:");

                if(course == null)
                {
                    log.error("webClient error: course == null");
                    throw new RuntimeException("Courses server error; object (course) equals null");
                }

            } catch (Exception e) {
                log.error("webClient courses error ");
                throw new RuntimeException("Courses server error");
            }

            if (user.getMoney().compareTo(course.getPrice()) > 0) {

                Double balance = user.getMoney() - course.getPrice();
                user.setMoney(balance);
                userRepository.update(user);

                PaymentAcceptEvent event = new PaymentAcceptEvent();
                event.setIdCourse(courseId);
                event.setIdUser(idUser);
                kafkaTemplateAccept.send("payment-course-events-topic", "payment", event);
                log.info("kafkaTemplateAccept send: " + event);

                return PaymentAcceptDto.builder()
                        .courseName(course.getName())
                        .userName(course.getName())
                        .cost(course.getPrice().toString())
                        .build();
            } else {
                log.warn("paymentCourse method: there are not enough funds in the account");
                throw new BadRequestException("there are not enough funds in the account");
            }

        } else {
            log.warn("paymentCourse method: user has this course");
            throw new NotFoundException("User or course not found");
        }
    }

    @Override
    public Boolean refundMonetToUserForCourse(Long idUser, Long idCourse) throws NotFoundException, RuntimeException {
        log.info(String.format("triggering method: refundMonetToUserForCourse with parameters: %s, %s", idUser, idCourse));
        UserEntity user = userRepository.findById(idUser).orElseThrow(() -> new NotFoundException("User not found"));
        Boolean courseHas;
        try {
            courseHas = webClientBuilder.build().get()
                    .uri("http://user-service/api/users/" + idUser + "/courses/" + idCourse + "/has")
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
            log.info(String.format("webClient users end work: course =  %s", courseHas));
        } catch (Exception e) {
            log.error("webClient user error ");
            throw new RuntimeException("User server error");
        }

        if (Boolean.TRUE.equals(courseHas)) {
            CourseDtoResponse course;
            try {
                course = webClientBuilder.build().get()
                        .uri("http://course-service/api/education/course/" + idCourse)
                        .retrieve()
                        .bodyToMono(CourseDtoResponse.class)
                        .block();
                log.info("webClient courses end work:");

                if(course == null)
                {
                    log.error("webClient error: course == null");
                    throw new RuntimeException("Courses server error; object (course) equals null");
                }

            } catch (Exception e) {
                log.error("webClient courses error ");
                throw new RuntimeException("Courses server error");
            }

            Double balance = user.getMoney() + course.getPrice();
            user.setMoney(balance);
            userRepository.update(user);

            PaymentReturnEvent event = new PaymentReturnEvent();
            event.setIdCourse(idCourse);
            event.setIdUser(idUser);
            kafkaTemplateReturn.send("payment-course-events-topic", "refund", event);

            log.info("finish method: refundMonetToUserForCourse with true");
            return true;
        } else {
            log.warn("finish method: refundMonetToUserForCourse with false");
            return false;
        }
    }
}
