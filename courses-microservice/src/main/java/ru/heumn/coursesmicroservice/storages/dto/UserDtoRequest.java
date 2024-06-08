package ru.heumn.coursesmicroservice.storages.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.heumn.coursesmicroservice.storages.entity.CourseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoRequest {

    String name;

    String lastname;

    String email;

    String password;

    Date dateCreate;

    Set<CourseEntity> courses = new HashSet<>();

    Double money;

    String role;
}
