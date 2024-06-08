package ru.heumn.userservice.service;

import ru.heumn.userservice.storages.dto.ChangePasswordDto;
import ru.heumn.userservice.storages.dto.CourseDtoResponse;
import ru.heumn.userservice.storages.dto.UserDtoRequest;
import ru.heumn.userservice.storages.dto.UserDtoResponse;
import ru.heumn.userservice.storages.exceptions.NotFoundException;

import java.util.List;

public interface UserService {

    List<UserDtoResponse> getAllUsers();

    UserDtoResponse getUserById(Long id) throws NotFoundException;

    UserDtoResponse addUser(UserDtoRequest userDtoRequest);

    UserDtoResponse updateUserById(Long id, UserDtoRequest userDtoRequest) throws NotFoundException;

    Boolean deleteUserById(Long id) throws NotFoundException;

    List<CourseDtoResponse> getCoursesByUser(Long id) throws NotFoundException;

    UserDtoResponse changeUserPasswordById(Long id, ChangePasswordDto changePasswordDto) throws NotFoundException;
}
