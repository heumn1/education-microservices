package ru.heumn.coursesmicroservice.service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.heumn.coursesmicroservice.factories.CourseDtoFactory;
import ru.heumn.coursesmicroservice.factories.StepDtoFactory;
import ru.heumn.coursesmicroservice.storages.dto.CourseDtoRequest;
import ru.heumn.coursesmicroservice.storages.dto.CourseDtoResponse;
import ru.heumn.coursesmicroservice.storages.dto.StepDtoResponse;
import ru.heumn.coursesmicroservice.storages.entity.CourseEntity;
import ru.heumn.coursesmicroservice.storages.entity.UserEntity;
import ru.heumn.coursesmicroservice.storages.exceptions.NotFoundException;
import ru.heumn.coursesmicroservice.storages.repository.CourseRepository;
import ru.heumn.coursesmicroservice.storages.repository.StepRepository;
import ru.heumn.coursesmicroservice.storages.repository.UserRepository;
import ru.heumn.coursesmicroservice.util.HibernateUtil;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class CourseServiceImpl implements CourseService{

    CourseRepository courseRepository = new CourseRepository(HibernateUtil.buildSessionFactory());
    StepRepository stepRepository = new StepRepository(HibernateUtil.buildSessionFactory());
    UserRepository userRepository = new UserRepository(HibernateUtil.buildSessionFactory());

    @Override
    public List<CourseDtoResponse> getAllCourses() {
        return CourseDtoFactory.makeDtoListCourse(courseRepository.findAll());
    }

    @Override
    public CourseDtoResponse getCourseById(Long id) throws NotFoundException {
        return CourseDtoFactory.makeCourseDtoResponse(
                courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found")));
    }

    @Override
    public CourseDtoResponse createCourse(CourseDtoRequest courseDtoRequest) {
        CourseEntity course = CourseDtoFactory.makeCourseEntity(courseDtoRequest);
        courseRepository.save(course);
        return CourseDtoFactory.makeCourseDtoResponse(course);
    }

    @Override
    public Boolean deleteCourse(Long id) throws NotFoundException {
        CourseEntity course = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found"));
        try {
            courseRepository.delete(course);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public CourseDtoResponse updateCourse(Long id, CourseDtoRequest courseDtoRequest) throws NotFoundException {
        CourseEntity course = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found"));
        course.setCategory(courseDtoRequest.getCategory());
        course.setTimeCourse(course.getTimeCourse());
        course.setDescription(course.getDescription());
        course.setPrice(course.getPrice());
        course.setRating(course.getRating());
        courseRepository.update(course);
        return CourseDtoFactory.makeCourseDtoResponse(course);
    }

    @Override
    public List<StepDtoResponse> getStepsByCourse(Long id){
        return StepDtoFactory.makeDtoListStep(stepRepository.getAllStepByIdCourse(id).stream().toList());
    }

    @Override
    public List<CourseDtoResponse> addCourseToUser(Long idUser, Long idCourse) throws NotFoundException {
        CourseEntity course = courseRepository.findById(idCourse).orElseThrow(() -> new NotFoundException("Course not found"));
        UserEntity user = userRepository.findById(idUser).orElseThrow(() -> new NotFoundException("User not found"));

        Set<CourseEntity> userCourses = user.getCourses();
        userCourses.add(course);
        user.setCourses(userCourses);
        userRepository.update(user);
        return userCourses.stream().map(CourseDtoFactory::makeCourseDtoResponse).collect(Collectors.toList());
    }

    @Override
    public List<CourseDtoResponse> deleteCourseFromUser(Long idUser, Long idCourse) throws NotFoundException {
        CourseEntity course = courseRepository.findById(idCourse).orElseThrow(() -> new NotFoundException("Course not found"));
        UserEntity user = userRepository.findById(idUser).orElseThrow(() -> new NotFoundException("User not found"));

        Set<CourseEntity> userCourses = user.getCourses();
        userCourses.remove(course);
        user.setCourses(userCourses);
        userRepository.update(user);
        return userCourses.stream().map(CourseDtoFactory::makeCourseDtoResponse).collect(Collectors.toList());
    }

}
