package ru.heumn.coursesmicroservice.service;

import org.springframework.stereotype.Service;
import ru.heumn.coursesmicroservice.factories.CommentDtoFactory;
import ru.heumn.coursesmicroservice.factories.StepDtoFactory;
import ru.heumn.coursesmicroservice.storages.dto.CommentDtoRequest;
import ru.heumn.coursesmicroservice.storages.dto.CommentDtoResponse;
import ru.heumn.coursesmicroservice.storages.dto.StepDtoRequest;
import ru.heumn.coursesmicroservice.storages.dto.StepDtoResponse;
import ru.heumn.coursesmicroservice.storages.entity.CommentEntity;
import ru.heumn.coursesmicroservice.storages.entity.StepEntity;
import ru.heumn.coursesmicroservice.storages.exceptions.NotFoundException;
import ru.heumn.coursesmicroservice.storages.repository.CommentRepository;
import ru.heumn.coursesmicroservice.storages.repository.CourseRepository;
import ru.heumn.coursesmicroservice.storages.repository.StepRepository;
import ru.heumn.coursesmicroservice.storages.repository.UserRepository;
import ru.heumn.coursesmicroservice.util.HibernateUtil;

import java.util.HashSet;
import java.util.List;

@Service
public class StepServiceImpl implements StepService{

    StepRepository stepRepository = new StepRepository(HibernateUtil.buildSessionFactory());
    CourseRepository courseRepository = new CourseRepository(HibernateUtil.buildSessionFactory());
    CommentRepository commentRepository = new CommentRepository(HibernateUtil.buildSessionFactory());
    UserRepository userRepository = new UserRepository(HibernateUtil.buildSessionFactory());

    @Override
    public List<StepDtoResponse> getAllSteps() {
        return StepDtoFactory.makeDtoListStep(stepRepository.findAll().stream().toList());
    }

    @Override
    public StepDtoResponse getStepsById(Long id) throws NotFoundException {
        return StepDtoFactory.makeStepDtoResponse(stepRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Step not found")));
    }

    @Override
    public StepDtoResponse addStep(StepDtoRequest stepDtoRequest) throws NotFoundException {
        StepEntity step = StepDtoFactory.makeStepEntity(stepDtoRequest);
        step.setCourse(courseRepository.findById(stepDtoRequest.getCourse()).orElseThrow(() -> new NotFoundException("course not found")));
        step.setComments(new HashSet<>());
        stepRepository.save(step);
        return StepDtoFactory.makeStepDtoResponse(step);
    }

    @Override
    public Boolean deleteStepsById(Long id) throws NotFoundException {
        StepEntity step = stepRepository.findById(id).orElseThrow(() -> new NotFoundException("Step not found"));
        try {
            stepRepository.delete(step);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public StepDtoResponse updateStepsById(Long id, StepDtoRequest stepDtoRequest) throws NotFoundException {
        StepEntity step = stepRepository.findById(id).orElseThrow(() -> new NotFoundException("Step not found"));

        step.setCourse(courseRepository.findById(stepDtoRequest.getCourse()).orElseThrow(() -> new NotFoundException("course not found")));
        step.setFileName(stepDtoRequest.getFileName());
        step.setName(stepDtoRequest.getName());
        stepRepository.update(step);
        return StepDtoFactory.makeStepDtoResponse(step);
    }

    @Override
    public CommentDtoResponse postCommentStepsById(Long id, CommentDtoRequest commentDtoRequest) throws NotFoundException {
        StepEntity step = stepRepository.findById(id).orElseThrow(() -> new NotFoundException("Step not found"));
        CommentEntity comment = CommentDtoFactory.makeCommentEntity(commentDtoRequest);
        comment.setUser(userRepository.findById(commentDtoRequest.getUserId()).orElseThrow(() -> new NotFoundException("User not found")));
        comment.setStep(step);
        commentRepository.save(comment);
        return CommentDtoFactory.makeCommentDtoResponse(comment);
    }

    @Override
    public Boolean deleteCommentStepsById(Long id) throws NotFoundException {
        CommentEntity comment = commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment not found"));
        try {
            commentRepository.delete(comment);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public CommentDtoResponse updateCommentStepsById(Long id, String text) throws NotFoundException {
        CommentEntity comment = commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment not found"));
        comment.setText(text);
        commentRepository.update(comment);
        return CommentDtoFactory.makeCommentDtoResponse(comment);
    }

    @Override
    public List<CommentDtoResponse> getCommentsStepsById(Long id) throws NotFoundException {
        StepEntity step = stepRepository.findById(id).orElseThrow(() -> new NotFoundException("Step not found"));
        return CommentDtoFactory.makeDtoListComment(step.getComments().stream().toList());
    }
}
