package com.minoo.taskmanager.repository;

import com.minoo.taskmanager.entity.Task;
import com.minoo.taskmanager.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserId(Long userId);

    List<Task> findByStatus(TaskStatus status);
}