package com.minoo.taskmanager.controller;

import com.minoo.taskmanager.dto.UserDto;
import com.minoo.taskmanager.dto.UserResponseDto;
import com.minoo.taskmanager.entity.User;
import com.minoo.taskmanager.mapper.UserMapper;
import com.minoo.taskmanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        List<UserResponseDto> response = users.stream()
                .map(UserMapper::mapToUserResponseDto)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        UserResponseDto response = UserMapper.mapToUserResponseDto(user);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserDto userDto) {
        User savedUser = userService.createUser(userDto);
        UserResponseDto response = UserMapper.mapToUserResponseDto(savedUser);

        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserDto userDto
    ) {
        User updatedUser = userService.updateUser(id, userDto);
        UserResponseDto response = UserMapper.mapToUserResponseDto(updatedUser);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}