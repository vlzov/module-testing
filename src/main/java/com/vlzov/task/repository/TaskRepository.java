package com.vlzov.task.repository;

import com.vlzov.task.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Task save(Task task);
    Optional<Task> findById(Long id);
    List<Task> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}