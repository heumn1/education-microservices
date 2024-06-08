package ru.heumn.paymentservice.storages.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.heumn.paymentservice.storages.entity.CourseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoResponse {

    Long id;

    String name;

    String lastname;

    String email;

    String password;

    Date dateCreate;

    String role;

    Double money;

    Set<CourseEntity> courses = new HashSet<>();
}
