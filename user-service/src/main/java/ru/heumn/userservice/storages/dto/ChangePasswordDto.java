package ru.heumn.userservice.storages.dto;

import lombok.Data;

@Data
public class ChangePasswordDto {

    String oldPassword;
    String newPassword;
}
