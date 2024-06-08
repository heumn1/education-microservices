package ru.heumn.coursesmicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.heumn.coursesmicroservice.service.CourseServiceImpl;
import ru.heumn.coursesmicroservice.storages.dto.CourseDtoRequest;
import ru.heumn.coursesmicroservice.storages.dto.CourseDtoResponse;
import ru.heumn.coursesmicroservice.storages.dto.StepDtoResponse;
import ru.heumn.coursesmicroservice.storages.exceptions.NotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/education/course")
public class CourseControllerImpl implements CourseController{

    @Autowired
    CourseServiceImpl courseService;

    @Override
    @GetMapping("/")
    public ResponseEntity<List<CourseDtoResponse>> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CourseDtoResponse> getCourseById(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<CourseDtoResponse> createCourse(@RequestBody CourseDtoRequest courseDtoRequest) {
        return new ResponseEntity<>(courseService.createCourse(courseDtoRequest), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCourse(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(courseService.deleteCourse(id), HttpStatus.NO_CONTENT);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<CourseDtoResponse> updateCourse(@PathVariable Long id, @RequestBody CourseDtoRequest courseDtoRequest) throws NotFoundException {
        return new ResponseEntity<>(courseService.updateCourse(id, courseDtoRequest), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}/step")
    public ResponseEntity<List<StepDtoResponse>> getStepsByCourse(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.getStepsByCourse(id), HttpStatus.OK);
    }

}
