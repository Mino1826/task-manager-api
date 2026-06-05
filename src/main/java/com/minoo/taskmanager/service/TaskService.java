package com.minoo.taskmanager.service;

import com.minoo.taskmanager.dto.TaskDto;
import com.minoo.taskmanager.entity.Task;
import com.minoo.taskmanager.entity.TaskStatus;
import com.minoo.taskmanager.entity.User;
import com.minoo.taskmanager.exception.ResourceNotFoundException;
import com.minoo.taskmanager.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Task> getTasksPagedAndSorted(int page, int size, String sortBy, String direction) {
        Sort sort;

        if (direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        return taskRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    @Transactional(readOnly = true)
    public List<Task> getTasksByUserId(Long userId) {
        userService.getUserById(userId);
        return taskRepository.findByUserId(userId);
    }

    @Transactional
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

    @Transactional
    public Task updateTask(Long id, TaskDto taskDto) {
        Task task = getTaskById(id);

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setDueDate(taskDto.getDueDate());

        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }
}