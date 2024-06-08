package ru.heumn.coursesmicroservice.service;

import ru.heumn.coursesmicroservice.storages.dto.CourseDtoRequest;
import ru.heumn.coursesmicroservice.storages.dto.CourseDtoResponse;
import ru.heumn.coursesmicroservice.storages.dto.StepDtoResponse;
import ru.heumn.coursesmicroservice.storages.exceptions.NotFoundException;

import java.util.List;

public interface CourseService {

    List<CourseDtoResponse> getAllCourses();

    CourseDtoResponse getCourseById(Long id) throws NotFoundException;

    CourseDtoResponse createCourse(CourseDtoRequest courseDtoRequest);

    Boolean deleteCourse(Long id) throws NotFoundException;

    CourseDtoResponse updateCourse(Long id, CourseDtoRequest courseDtoRequest) throws NotFoundException;

    List<StepDtoResponse> getStepsByCourse(Long id);

    List<CourseDtoResponse> addCourseToUser(Long idUser, Long idCourse) throws NotFoundException;

    List<CourseDtoResponse> deleteCourseFromUser(Long idUser, Long idCourse) throws NotFoundException;
}
