package ru.heumn.paymentservice.storages.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoRequest {

    String name;

    String lastname;

    String email;

    String password;

    String role;

    Double money;
}
