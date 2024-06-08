package ru.heumn.userservice.storages.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.heumn.userservice.storages.entity.StepEntity;
import ru.heumn.userservice.storages.entity.UserEntity;

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
