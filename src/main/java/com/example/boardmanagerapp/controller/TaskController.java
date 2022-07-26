package com.example.boardmanagerapp.controller;

import com.example.boardmanagerapp.dto.request.SectionRequestDto;
import com.example.boardmanagerapp.dto.request.TaskRequestDto;
import com.example.boardmanagerapp.dto.response.TaskResponseDto;
import com.example.boardmanagerapp.service.TaskService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/sections/{id}")
    @ApiOperation(value = "Create task")
    public TaskResponseDto createTask(@PathVariable Long id,
                                      @RequestBody TaskRequestDto taskRequestDto) {
        return taskService.createTask(id, taskRequestDto);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update task by id")
    public TaskResponseDto updateTaskById(@PathVariable Long id,
                                          @RequestBody TaskRequestDto taskRequestDto) {
        return taskService.updateTaskById(id, taskRequestDto);
    }

    @PutMapping("/{id}/update-status")
    @ApiOperation(value = "Update task by section")
    public TaskResponseDto updateStatus(@PathVariable Long id,
                                        @RequestBody SectionRequestDto sectionRequestDto) {
        return taskService.updateStatus(id, sectionRequestDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get task by id")
    public TaskResponseDto getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping
    @ApiOperation(value = "Get all tasks")
    public List<TaskResponseDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete task by id")
    public TaskResponseDto deleteTaskById(@PathVariable Long id) {
        return taskService.deleteTasksById(id);
    }
}
