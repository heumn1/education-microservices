package ru.heumn.userservice.factories;

import com.fasterxml.jackson.databind.util.ObjectBuffer;
import org.springframework.stereotype.Component;
import ru.heumn.userservice.storages.dto.UserDtoRequest;
import ru.heumn.userservice.storages.dto.UserDtoResponse;
import ru.heumn.userservice.storages.entity.UserEntity;

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
                .money(object.getMoney())
                .courses(object.getCourses())
                .build();
    }

    public static UserEntity makeUserEntity(UserDtoRequest object){
        return UserEntity.builder()
                .name(object.getName())
                .email(object.getEmail())
                .password(object.getPassword())
                .lastname(object.getLastname())
                .money(object.getMoney())
                .role(object.getRole())
                .build();
    }

    public static List<UserDtoResponse> makeDtoListUser(List<UserEntity> list) {

        return list.stream().map(UserDtoFactory::makeUserDtoResponse).collect(Collectors.toList());
    }
}
