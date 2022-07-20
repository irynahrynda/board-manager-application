package com.example.boardmanagerapp.service.impl;

import com.example.boardmanagerapp.model.Task;
import com.example.boardmanagerapp.repository.TaskRepository;
import com.example.boardmanagerapp.service.TaskService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get task by id " + id));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteTasksById(Long id) {
        taskRepository.deleteById(id);
    }
}
