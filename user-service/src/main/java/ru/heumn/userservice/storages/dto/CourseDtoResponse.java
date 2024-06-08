package ru.heumn.userservice.storages.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDtoResponse {

    Long id;

    String name;

    String description;

    String timeCourse;

    Double price;

    String category;

    Float rating;
}
