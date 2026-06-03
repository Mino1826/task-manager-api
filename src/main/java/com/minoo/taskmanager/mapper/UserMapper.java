package com.minoo.taskmanager.mapper;

import com.minoo.taskmanager.dto.UserResponseDto;
import com.minoo.taskmanager.entity.User;

public class UserMapper {

    public static UserResponseDto mapToUserResponseDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}