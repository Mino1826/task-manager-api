package com.minoo.taskmanager.controller;

import com.minoo.taskmanager.dto.TaskDto;
import com.minoo.taskmanager.dto.TaskResponseDto;
import com.minoo.taskmanager.entity.Task;
import com.minoo.taskmanager.mapper.TaskMapper;
import com.minoo.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();

        List<TaskResponseDto> response = tasks.stream()
                .map(TaskMapper::mapToTaskResponseDto)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        TaskResponseDto response = TaskMapper.mapToTaskResponseDto(task);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<TaskResponseDto>> getTasksByUserId(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);

        List<TaskResponseDto> response = tasks.stream()
                .map(TaskMapper::mapToTaskResponseDto)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<TaskResponseDto> createTask(
            @PathVariable Long userId,
            @Valid @RequestBody TaskDto taskDto
    ) {
        Task savedTask = taskService.createTask(userId, taskDto);
        TaskResponseDto response = TaskMapper.mapToTaskResponseDto(savedTask);

        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskDto taskDto
    ) {
        Task updatedTask = taskService.updateTask(id, taskDto);
        TaskResponseDto response = TaskMapper.mapToTaskResponseDto(updatedTask);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}