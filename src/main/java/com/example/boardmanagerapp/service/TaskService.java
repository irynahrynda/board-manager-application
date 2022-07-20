package com.example.boardmanagerapp.service;

import com.example.boardmanagerapp.model.Column;
import com.example.boardmanagerapp.model.Task;

import java.util.List;

public interface TaskService {
    Task createTask(Task task);

    Task getTaskById(Long id);

    List<Task> getAllTasks();

    void deleteTasksById(Long id);
}
