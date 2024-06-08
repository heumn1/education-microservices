package ru.heumn.paymentservice.storages.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
