package ru.heumn.coursesmicroservice.storages.dto;


import jakarta.persistence.*;
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
public class CommentDtoRequest {

    String text;

    Long stepId;

    Long userId;
}
