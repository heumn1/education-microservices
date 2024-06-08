package ru.heumn.coursesmicroservice.controllers;

import org.springframework.http.ResponseEntity;
import ru.heumn.coursesmicroservice.storages.dto.CommentDtoRequest;
import ru.heumn.coursesmicroservice.storages.dto.CommentDtoResponse;
import ru.heumn.coursesmicroservice.storages.dto.StepDtoRequest;
import ru.heumn.coursesmicroservice.storages.dto.StepDtoResponse;
import ru.heumn.coursesmicroservice.storages.exceptions.NotFoundException;

import java.util.List;

public interface StepController {

    ResponseEntity<List<StepDtoResponse>> getAllSteps();

    ResponseEntity<StepDtoResponse> getStepsById(Long id) throws NotFoundException;

    ResponseEntity<StepDtoResponse> addSteps(StepDtoRequest stepDtoRequest) throws NotFoundException;

    ResponseEntity<Boolean> deleteStepsById(Long id) throws NotFoundException;

    ResponseEntity<StepDtoResponse> updateStepsById(Long id, StepDtoRequest step) throws NotFoundException;

    ResponseEntity<List<CommentDtoResponse>> getCommentsStepsById(Long id) throws NotFoundException;

    ResponseEntity<CommentDtoResponse> postCommentStepsById(Long id, CommentDtoRequest commentDtoRequest) throws NotFoundException;

    ResponseEntity<Boolean> deleteCommentStepsById(Long id) throws NotFoundException;

    ResponseEntity<CommentDtoResponse> updateCommentStepsById(Long id, String text) throws NotFoundException;
}
