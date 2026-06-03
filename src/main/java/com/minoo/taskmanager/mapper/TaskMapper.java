package com.minoo.taskmanager.mapper;

import com.minoo.taskmanager.dto.TaskResponseDto;
import com.minoo.taskmanager.entity.Task;
import com.minoo.taskmanager.entity.User;

public class TaskMapper {

    public static TaskResponseDto mapToTaskResponseDto(Task task) {
        User user = task.getUser();

        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getDueDate(),
                user.getId(),
                user.getName()
        );
    }
}