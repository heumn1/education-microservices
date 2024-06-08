package ru.heumn.userservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.heumn.userservice.service.UserServiceImpl;
import ru.heumn.userservice.storages.dto.ChangePasswordDto;
import ru.heumn.userservice.storages.dto.CourseDtoResponse;
import ru.heumn.userservice.storages.dto.UserDtoRequest;
import ru.heumn.userservice.storages.dto.UserDtoResponse;
import ru.heumn.userservice.storages.exceptions.NotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserControllerImpl implements UserController {

    @Autowired
    private UserServiceImpl userService;

    @Override
    @GetMapping("/")
    public ResponseEntity<List<UserDtoResponse>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserDtoResponse> getUserById(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<UserDtoResponse> addUser(@RequestBody UserDtoRequest userDtoRequest) {
        return new ResponseEntity<>(userService.addUser(userDtoRequest), HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<UserDtoResponse> updateUserById(@PathVariable Long id, @RequestBody UserDtoRequest userDtoRequest) throws NotFoundException {
        return new ResponseEntity<>(userService.updateUserById(id,userDtoRequest), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}/courses")
    public ResponseEntity<List<CourseDtoResponse>> getCoursesByUser(@PathVariable Long id) throws NotFoundException {
        return new ResponseEntity<>(userService.getCoursesByUser(id), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{idUser}/courses/{idCourse}/has")
    public ResponseEntity<Boolean> userHasCourse(@PathVariable Long idUser, @PathVariable Long idCourse) throws NotFoundException {
        return new ResponseEntity<>(userService.userHasCourse(idUser, idCourse), HttpStatus.OK);
    }

    @Override
    @PatchMapping("/{id}/password")
    public ResponseEntity<UserDtoResponse> changeUserPasswordById(@PathVariable Long id, @RequestBody ChangePasswordDto changePasswordDto) throws NotFoundException {
        return new ResponseEntity<>(userService.changeUserPasswordById(id, changePasswordDto), HttpStatus.OK);
    }
}
