package ru.heumn.coursesmicroservice.storages.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.heumn.coursesmicroservice.storages.entity.UserEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDtoRequest {

    String name;

    String description;

    String timeCourse;

    Double price;

    String category;

    Float rating;

}
