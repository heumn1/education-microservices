package ru.heumn.paymentservice.factories;

import org.springframework.stereotype.Component;
import ru.heumn.paymentservice.storages.dto.CourseDtoRequest;
import ru.heumn.paymentservice.storages.dto.CourseDtoResponse;
import ru.heumn.paymentservice.storages.entity.CourseEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseDtoFactory {

    public static CourseDtoResponse makeCourseDtoResponse(CourseEntity object){
        return CourseDtoResponse.builder()
                .id(object.getId())
                .name(object.getName())
                .category(object.getCategory())
                .timeCourse(object.getTimeCourse())
                .description(object.getDescription())
                .price(object.getPrice())
                .rating(object.getRating())
                .build();
    }

    public static CourseEntity makeCourseEntity(CourseDtoRequest object){
        return CourseEntity.builder()
                .name(object.getName())
                .category(object.getCategory())
                .timeCourse(object.getTimeCourse())
                .description(object.getDescription())
                .price(object.getPrice())
                .rating(object.getRating())
                .build();
    }

    public static List<CourseDtoResponse> makeDtoListCourse(List<CourseEntity> list) {

        return list.stream().map(CourseDtoFactory::makeCourseDtoResponse).collect(Collectors.toList());
    }
}
