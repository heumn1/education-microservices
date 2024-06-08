package ru.heumn.coursesmicroservice.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.heumn.coursesmicroservice.service.CourseServiceImpl;
import ru.heumn.coursesmicroservice.storages.event.PaymentAcceptEvent;
import ru.heumn.coursesmicroservice.storages.event.PaymentReturnEvent;
import ru.heumn.coursesmicroservice.storages.exceptions.NotFoundException;

@Component
@KafkaListener(topics = "payment-course-events-topic")
@Log4j2
@RequiredArgsConstructor
public class PaymentToCourseTopicHandler {

    private final CourseServiceImpl courseService;

    @KafkaHandler
    public void handlerAcceptPayment(PaymentAcceptEvent event) throws NotFoundException {
        log.info("triggering handler handlerAcceptPayment in payment-course-events-topic event: " + event);
        courseService.addCourseToUser(event.getIdUser(), event.getIdCourse());
    }

    @KafkaHandler
    public void handlerRefundPayment(PaymentReturnEvent event) throws NotFoundException {
        log.info("triggering handler handlerAcceptPayment in payment-course-events-topic event: " + event);
        courseService.deleteCourseFromUser(event.getIdUser(), event.getIdCourse());
    }
}
