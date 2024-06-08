package ru.heumn.coursesmicroservice.factories;

import org.springframework.stereotype.Component;
import ru.heumn.coursesmicroservice.storages.dto.StepDtoRequest;
import ru.heumn.coursesmicroservice.storages.dto.StepDtoResponse;
import ru.heumn.coursesmicroservice.storages.entity.StepEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StepDtoFactory {

    public static StepDtoResponse makeStepDtoResponse(StepEntity object){
        return StepDtoResponse.builder()
                .id(object.getId())
                .name(object.getName())
                .fileName(object.getFileName())
                .course(object.getCourse())
                .comments(object.getComments())
                .build();
    }

    public static StepEntity makeStepEntity(StepDtoRequest object){
        return StepEntity.builder()
                .name(object.getName())
                .fileName(object.getFileName())
                .build();
    }

    public static List<StepDtoResponse> makeDtoListStep(List<StepEntity> list) {

        return list.stream().map(StepDtoFactory::makeStepDtoResponse).collect(Collectors.toList());
    }

}
