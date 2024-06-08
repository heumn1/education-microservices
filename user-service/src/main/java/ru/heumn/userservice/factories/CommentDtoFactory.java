package ru.heumn.userservice.factories;

import org.springframework.stereotype.Component;
import ru.heumn.userservice.storages.dto.CommentDtoRequest;
import ru.heumn.userservice.storages.dto.CommentDtoResponse;
import ru.heumn.userservice.storages.entity.CommentEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentDtoFactory {

    public static CommentDtoResponse makeCommentDtoResponse(CommentEntity object){
        return CommentDtoResponse.builder()
                .id(object.getId())
                .text(object.getText())
                .step(object.getStep())
                .user(object.getUser())
                .build();
    }

    public static CommentEntity makeCommentEntity(CommentDtoRequest object){
        return CommentEntity.builder()
                .text(object.getText())
                .step(object.getStep())
                .user(object.getUser())
                .build();
    }

    public static List<CommentDtoResponse> makeDtoListComment(List<CommentEntity> list) {

        return list.stream().map(CommentDtoFactory::makeCommentDtoResponse).collect(Collectors.toList());
    }
}
