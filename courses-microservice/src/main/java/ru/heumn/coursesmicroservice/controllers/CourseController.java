package ru.heumn.coursesmicroservice.controllers;

import org.springframework.http.ResponseEntity;
import ru.heumn.coursesmicroservice.storages.dto.CourseDtoRequest;
import ru.heumn.coursesmicroservice.storages.dto.CourseDtoResponse;
import ru.heumn.coursesmicroservice.storages.dto.StepDtoResponse;
import ru.heumn.coursesmicroservice.storages.exceptions.NotFoundException;

import java.util.List;

public interface CourseController {

    ResponseEntity<List<CourseDtoResponse>> getAllCourses();

    ResponseEntity<CourseDtoResponse> getCourseById(Long id) throws NotFoundException;

    ResponseEntity<CourseDtoResponse> createCourse(CourseDtoRequest courseDtoRequest);

    ResponseEntity<Boolean> deleteCourse(Long id) throws NotFoundException;

    ResponseEntity<CourseDtoResponse> updateCourse(Long id, CourseDtoRequest courseDtoRequest) throws NotFoundException;

    ResponseEntity<List<StepDtoResponse>> getStepsByCourse(Long id);
}
