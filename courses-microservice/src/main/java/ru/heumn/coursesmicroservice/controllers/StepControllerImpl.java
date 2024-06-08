package ru.heumn.coursesmicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.heumn.coursesmicroservice.service.StepServiceImpl;
import ru.heumn.coursesmicroservice.storages.dto.CommentDtoRequest;
import ru.heumn.coursesmicroservice.storages.dto.CommentDtoResponse;
import ru.heumn.coursesmicroservice.storages.dto.StepDtoRequest;
import ru.heumn.coursesmicroservice.storages.dto.StepDtoResponse;
import ru.heumn.coursesmicroservice.storages.entity.UserEntity;
import ru.heumn.coursesmicroservice.storages.exceptions.NotFoundException;
import ru.heumn.coursesmicroservice.storages.repository.UserRepository;
import ru.heumn.coursesmicroservice.util.HibernateUtil;

import javax.xml.crypto.Data;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/education/step")
public class StepControllerImpl implements StepController{

    @Autowired
    StepServiceImpl stepService;

    @Override
    @GetMapping("/")
    public ResponseEntity<List<StepDtoResponse>> getAllSteps() {
        return new ResponseEntity<>(stepService.getAllSteps(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<StepDtoResponse> getStepsById(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(stepService.getStepsById(id), HttpStatus.OK);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<StepDtoResponse> addSteps(@RequestBody StepDtoRequest stepDtoRequest) throws NotFoundException {
        return new ResponseEntity<>(stepService.addStep(stepDtoRequest), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteStepsById(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(stepService.deleteStepsById(id), HttpStatus.NO_CONTENT);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<StepDtoResponse> updateStepsById(@PathVariable Long id, @RequestBody StepDtoRequest step) throws NotFoundException {
        return new ResponseEntity<>(stepService.updateStepsById(id, step), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDtoResponse>> getCommentsStepsById(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(stepService.getCommentsStepsById(id), HttpStatus.OK);
    }

    @Override
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDtoResponse> postCommentStepsById(@PathVariable Long id, @RequestBody CommentDtoRequest commentDtoRequest) throws NotFoundException {
        return new ResponseEntity<>(stepService.postCommentStepsById(id, commentDtoRequest), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Boolean> deleteCommentStepsById(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(stepService.deleteCommentStepsById(id), HttpStatus.OK);
    }

    @Override
    @PatchMapping("/comments/{id}")
    public ResponseEntity<CommentDtoResponse> updateCommentStepsById(@PathVariable Long id, @RequestBody String text) throws NotFoundException {
        return new ResponseEntity<>(stepService.updateCommentStepsById(id, text), HttpStatus.OK);
    }
}
