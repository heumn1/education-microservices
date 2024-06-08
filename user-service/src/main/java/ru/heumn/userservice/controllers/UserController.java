package ru.heumn.userservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.heumn.userservice.storages.dto.ChangePasswordDto;
import ru.heumn.userservice.storages.dto.CourseDtoResponse;
import ru.heumn.userservice.storages.dto.UserDtoRequest;
import ru.heumn.userservice.storages.dto.UserDtoResponse;
import ru.heumn.userservice.storages.exceptions.NotFoundException;

import java.util.List;

public interface UserController {

    ResponseEntity<List<UserDtoResponse>> getAllUsers();

    ResponseEntity<UserDtoResponse> getUserById(Long id) throws NotFoundException;

    ResponseEntity<UserDtoResponse> addUser(UserDtoRequest userDtoRequest);

    ResponseEntity<UserDtoResponse> updateUserById(Long id, UserDtoRequest userDtoRequest) throws NotFoundException;

    ResponseEntity<Boolean> deleteUserById(Long id) throws NotFoundException;

    ResponseEntity<List<CourseDtoResponse>> getCoursesByUser(Long id) throws NotFoundException;

    @GetMapping("/{idUser}/courses/{idCourse}/has")
    ResponseEntity<Boolean> userHasCourse(@PathVariable Long idUser, @PathVariable Long idCourse) throws NotFoundException;

    ResponseEntity<UserDtoResponse> changeUserPasswordById(Long id, ChangePasswordDto changePasswordDto) throws NotFoundException;
}
