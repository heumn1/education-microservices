package ru.heumn.paymentservice.storages.dto;

import lombok.Data;

@Data
public class ChangePasswordDto {

    String oldPassword;
    String newPassword;
}
