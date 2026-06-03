package com.minoo.taskmanager.controller;

import com.minoo.taskmanager.dto.TaskDto;
import com.minoo.taskmanager.entity.Task;
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
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<Task> createTask(
            @PathVariable Long userId,
            @Valid @RequestBody TaskDto taskDto
    ) {
        Task savedTask = taskService.createTask(userId, taskDto);
        return ResponseEntity.status(201).body(savedTask);
    }
}