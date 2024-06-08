package ru.heumn.userservice.service;

import org.springframework.stereotype.Service;
import ru.heumn.userservice.factories.CourseDtoFactory;
import ru.heumn.userservice.factories.UserDtoFactory;
import ru.heumn.userservice.storages.dto.ChangePasswordDto;
import ru.heumn.userservice.storages.dto.CourseDtoResponse;
import ru.heumn.userservice.storages.dto.UserDtoRequest;
import ru.heumn.userservice.storages.dto.UserDtoResponse;
import ru.heumn.userservice.storages.entity.CourseEntity;
import ru.heumn.userservice.storages.entity.UserEntity;
import ru.heumn.userservice.storages.exceptions.NotFoundException;
import ru.heumn.userservice.storages.repository.UserRepository;
import ru.heumn.userservice.util.HibernateUtil;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository = new UserRepository(HibernateUtil.buildSessionFactory());

    @Override
    public List<UserDtoResponse> getAllUsers() {
        return UserDtoFactory.makeDtoListUser(userRepository.findAll());
    }

    @Override
    public UserDtoResponse getUserById(Long id) throws NotFoundException {
        return UserDtoFactory.makeUserDtoResponse(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User nof found")));
    }

    @Override
    public UserDtoResponse addUser(UserDtoRequest userDtoRequest) {
        UserEntity user = UserDtoFactory.makeUserEntity(userDtoRequest);
        user.setDateCreate(Date.from(Instant.now()));
        user.setMoney((double) 0);
        userRepository.save(user);
        return UserDtoFactory.makeUserDtoResponse(user);
    }

    @Override
    public UserDtoResponse updateUserById(Long id, UserDtoRequest userDtoRequest) throws NotFoundException {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User nof found"));
        user.setEmail(userDtoRequest.getEmail());
        user.setName(userDtoRequest.getName());
        user.setLastname(userDtoRequest.getLastname());
        user.setPassword(userDtoRequest.getPassword());
        userRepository.update(user);
        return UserDtoFactory.makeUserDtoResponse(user);
    }

    @Override
    public Boolean deleteUserById(Long id) throws NotFoundException {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User nof found"));
        try {
            userRepository.delete(user);
            return true;
        }
        catch (Exception exception)
        {
            return false;
        }
    }

    @Override
    public List<CourseDtoResponse> getCoursesByUser(Long id) throws NotFoundException {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User nof found"));
        return CourseDtoFactory.makeDtoListCourse(user.getCourses().stream().toList());
    }

    @Override
    public UserDtoResponse changeUserPasswordById(Long id, ChangePasswordDto changePasswordDto) throws NotFoundException {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User nof found"));
        if(user.getPassword().equals(changePasswordDto.getOldPassword())) {
            user.setPassword(changePasswordDto.getNewPassword());
        }
        return UserDtoFactory.makeUserDtoResponse(user);
    }

    public Boolean userHasCourse(Long idUser, Long idCourse) throws NotFoundException {
        UserEntity user = userRepository.findById(idUser).orElseThrow(() -> new NotFoundException("User nof found"));
        List<CourseEntity> course = user.getCourses().stream().filter(courses -> courses.getId().equals(idCourse)).toList();

        return !course.isEmpty();
    }
}
