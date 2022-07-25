package com.example.boardmanagerapp.controller;

import com.example.boardmanagerapp.dto.request.ColumnRequestDto;
import com.example.boardmanagerapp.dto.request.TaskRequestDto;
import com.example.boardmanagerapp.dto.response.ColumnResponseDto;
import com.example.boardmanagerapp.dto.response.TaskResponseDto;
import com.example.boardmanagerapp.mapper.MapperToDto;
import com.example.boardmanagerapp.mapper.MapperToModel;
import com.example.boardmanagerapp.model.Columnn;
import com.example.boardmanagerapp.model.Task;
import com.example.boardmanagerapp.service.ColumnService;
import com.example.boardmanagerapp.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final ColumnService columnService;
    private final MapperToModel<TaskRequestDto, Task> mapperToModel;
    private final MapperToDto<TaskResponseDto, Task> mapperToDto;

    private final MapperToDto<ColumnResponseDto, Columnn> columnMapperToDto;
    private final MapperToModel<ColumnRequestDto, Columnn> columnMapperToModel;


    public TaskController(TaskService taskService, ColumnService columnService,
                          MapperToModel<TaskRequestDto, Task> mapperToModel,
                          MapperToDto<TaskResponseDto, Task> mapperToDto, MapperToDto<ColumnResponseDto, Columnn> columnMapperToDto, MapperToModel<ColumnRequestDto, Columnn> columnMapperToModel) {
        this.taskService = taskService;
        this.columnService = columnService;
        this.mapperToModel = mapperToModel;
        this.mapperToDto = mapperToDto;
        this.columnMapperToDto = columnMapperToDto;
        this.columnMapperToModel = columnMapperToModel;
    }

    @PostMapping("/columns/{id}")
    public TaskResponseDto createTask(@PathVariable Long id, @RequestBody TaskRequestDto taskRequestDto) {
        Task task = mapperToModel.mapToModel(taskRequestDto);
        Columnn columnn = columnService.getColumnById(id);
        task.setColumn(columnn);
        Task taskWithColumns = taskService.createTask(task);
        if (taskWithColumns != null) {
            List<Task> tasks = columnn.getTasks();
            tasks.add(taskWithColumns);
            columnn.setTasks(tasks);
            columnService.createColumn(columnn);
        }

        return mapperToDto.mapToDto(taskWithColumns);
    }

    @PutMapping("/{id}")
    public TaskResponseDto updateTaskById(@PathVariable Long id,
                                          @RequestBody TaskRequestDto taskRequestDto) {
        Task task = taskService.getTaskById(id);
        if (taskRequestDto.getTitle() != null) {
            task.setTitle(taskRequestDto.getTitle());
        }
        if (taskRequestDto.getDescription() != null) {
            task.setDescription(taskRequestDto.getDescription());
        }

        Task updatedTask = taskService.createTask(task);
        return mapperToDto.mapToDto(updatedTask);
    }

    @PutMapping("/{id}/update-status")
    public TaskResponseDto updateTaskByIdByColumn(@PathVariable Long id,
                                                  @RequestBody ColumnRequestDto columnRequestDto) {
        Task taskById = taskService.getTaskById(id);
        Columnn columnn = columnService.getColumnByName(columnRequestDto.getName());
        taskById.setColumn(columnService.getColumnById(columnn.getId()));
        Task taskUpdated = taskService.createTask(taskById);
        return mapperToDto.mapToDto(taskUpdated);
    }

    @GetMapping("/{id}")
    public TaskResponseDto getTaskById(@PathVariable Long id) {
        return mapperToDto.mapToDto(taskService.getTaskById(id));
    }

    @GetMapping
    public List<TaskResponseDto> getAll() {
        return taskService.getAllTasks().stream()
                .map(mapperToDto::mapToDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public TaskResponseDto deleteTaskById(@PathVariable Long id) {
        TaskResponseDto taskResponseDto = mapperToDto.mapToDto(taskService.deleteTasksById(id));
        return taskResponseDto;
    }
}
