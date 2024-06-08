package ru.heumn.coursesmicroservice.storages.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.heumn.coursesmicroservice.storages.entity.StepEntity;
import ru.heumn.coursesmicroservice.storages.entity.UserEntity;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDtoResponse {

    Long id;

    String text;

    StepEntity step;

    UserEntity user;
}
