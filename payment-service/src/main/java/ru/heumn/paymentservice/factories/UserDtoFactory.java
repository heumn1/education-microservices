package ru.heumn.paymentservice.factories;

import org.springframework.stereotype.Component;
import ru.heumn.paymentservice.storages.dto.UserDtoRequest;
import ru.heumn.paymentservice.storages.dto.UserDtoResponse;
import ru.heumn.paymentservice.storages.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoFactory {

    public static UserDtoResponse makeUserDtoResponse(UserEntity object){
        return UserDtoResponse.builder()
                .id(object.getId())
                .name(object.getName())
                .email(object.getEmail())
                .dateCreate(object.getDateCreate())
                .password(object.getPassword())
                .lastname(object.getLastname())
                .role(object.getRole())
                .courses(object.getCourses())
                .money(object.getMoney())
                .build();
    }

    public static UserEntity makeUserEntity(UserDtoRequest object){
        return UserEntity.builder()
                .name(object.getName())
                .email(object.getEmail())
                .password(object.getPassword())
                .lastname(object.getLastname())
                .role(object.getRole())
                .money(object.getMoney())
                .build();
    }

    public static List<UserDtoResponse> makeDtoListUser(List<UserEntity> list) {

        return list.stream().map(UserDtoFactory::makeUserDtoResponse).collect(Collectors.toList());
    }
}
