package ru.heumn.coursesmicroservice.storages.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.heumn.coursesmicroservice.storages.entity.CommentEntity;
import ru.heumn.coursesmicroservice.storages.entity.CourseEntity;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StepDtoResponse {

    Long id;

    String name;

    String fileName;

    CourseEntity course;

    Set<CommentEntity> comments = new HashSet<>();
}
