package com.minoo.taskmanager.service;

import com.minoo.taskmanager.dto.TaskDto;
import com.minoo.taskmanager.entity.Task;
import com.minoo.taskmanager.entity.User;
import com.minoo.taskmanager.exception.ResourceNotFoundException;
import com.minoo.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    public List<Task> getTasksByUserId(Long userId) {
        userService.getUserById(userId);
        return taskRepository.findByUserId(userId);
    }

    public Task createTask(Long userId, TaskDto taskDto) {
        User user = userService.getUserById(userId);

        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setDueDate(taskDto.getDueDate());
        task.setUser(user);

        return taskRepository.save(task);
    }

    public Task updateTask(Long id, TaskDto taskDto) {
        Task task = getTaskById(id);

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setDueDate(taskDto.getDueDate());

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }
}