package ru.heumn.userservice.factories;

import org.springframework.stereotype.Component;
import ru.heumn.userservice.storages.dto.StepDtoRequest;
import ru.heumn.userservice.storages.dto.StepDtoResponse;
import ru.heumn.userservice.storages.entity.StepEntity;

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
                .build();
    }

    public static StepEntity makeStepEntity(StepDtoRequest object){
        return StepEntity.builder()
                .name(object.getName())
                .fileName(object.getFileName())
                .course(object.getCourse())
                .build();
    }

    public static List<StepDtoResponse> makeDtoListStep(List<StepEntity> list) {

        return list.stream().map(StepDtoFactory::makeStepDtoResponse).collect(Collectors.toList());
    }

}
