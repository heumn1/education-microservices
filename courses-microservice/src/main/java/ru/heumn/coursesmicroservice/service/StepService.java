package ru.heumn.coursesmicroservice.service;

import ru.heumn.coursesmicroservice.storages.dto.CommentDtoRequest;
import ru.heumn.coursesmicroservice.storages.dto.CommentDtoResponse;
import ru.heumn.coursesmicroservice.storages.dto.StepDtoRequest;
import ru.heumn.coursesmicroservice.storages.dto.StepDtoResponse;
import ru.heumn.coursesmicroservice.storages.exceptions.NotFoundException;

import java.util.List;

public interface StepService {

    List<StepDtoResponse> getAllSteps();

    StepDtoResponse getStepsById(Long id) throws NotFoundException;

    StepDtoResponse addStep(StepDtoRequest step) throws NotFoundException;

    Boolean deleteStepsById(Long id) throws NotFoundException;

    StepDtoResponse updateStepsById(Long id, StepDtoRequest step) throws NotFoundException;

    CommentDtoResponse postCommentStepsById(Long id, CommentDtoRequest commentDtoRequest) throws NotFoundException;

    Boolean deleteCommentStepsById(Long id) throws NotFoundException;

    CommentDtoResponse updateCommentStepsById(Long id, String text) throws NotFoundException;

    List<CommentDtoResponse> getCommentsStepsById(Long id) throws NotFoundException;
}
