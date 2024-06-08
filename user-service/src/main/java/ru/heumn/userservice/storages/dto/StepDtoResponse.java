package ru.heumn.userservice.storages.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.heumn.userservice.storages.entity.CourseEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StepDtoResponse {

    Long id;

    String name;

    String fileName;

    CourseEntity course;
}
