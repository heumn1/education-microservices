package ru.heumn.userservice.storages.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.heumn.userservice.storages.entity.CourseEntity;

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

    Double money;

    String role;
}
