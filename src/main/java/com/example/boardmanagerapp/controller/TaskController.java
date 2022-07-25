package com.example.boardmanagerapp.controller;

import com.example.boardmanagerapp.dto.request.SectionRequestDto;
import com.example.boardmanagerapp.dto.request.TaskRequestDto;
import com.example.boardmanagerapp.dto.response.SectionResponseDto;
import com.example.boardmanagerapp.dto.response.TaskResponseDto;
import com.example.boardmanagerapp.mapper.MapperToDto;
import com.example.boardmanagerapp.mapper.MapperToModel;
import com.example.boardmanagerapp.model.Section;
import com.example.boardmanagerapp.model.Task;
import com.example.boardmanagerapp.service.SectionService;
import com.example.boardmanagerapp.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final SectionService sectionService;
    private final MapperToModel<TaskRequestDto, Task> mapperToModel;
    private final MapperToDto<TaskResponseDto, Task> mapperToDto;

    private final MapperToDto<SectionResponseDto, Section> sectionMapperToDto;
    private final MapperToModel<SectionRequestDto, Section> sectionMapperToModel;

    public TaskController(TaskService taskService, SectionService sectionService, MapperToModel<TaskRequestDto, Task> mapperToModel, MapperToDto<TaskResponseDto, Task> mapperToDto, MapperToDto<SectionResponseDto, Section> sectionMapperToDto, MapperToModel<SectionRequestDto, Section> sectionMapperToModel) {
        this.taskService = taskService;
        this.sectionService = sectionService;
        this.mapperToModel = mapperToModel;
        this.mapperToDto = mapperToDto;
        this.sectionMapperToDto = sectionMapperToDto;
        this.sectionMapperToModel = sectionMapperToModel;
    }


    @PostMapping("/sections/{id}")
    public TaskResponseDto createTask(@PathVariable Long id, @RequestBody TaskRequestDto taskRequestDto) {
        Task task = mapperToModel.mapToModel(taskRequestDto);
        Section section = sectionService.getSectionById(id);
        task.setSection(section);
        Task taskWithColumns = taskService.createTask(task);
        if (taskWithColumns != null) {
            List<Task> tasks = section.getTasks();
            tasks.add(taskWithColumns);
            section.setTasks(tasks);
            sectionService.createSection(section);
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
    public TaskResponseDto updateStatus(@PathVariable Long id,
                                                  @RequestBody SectionRequestDto sectionRequestDto) {
        Task task = taskService.getTaskById(id);
        Section section = sectionService.getSectionByName(sectionRequestDto.getName());
        if (task != null && section != null) {
            task.setSection(section);
        }
        Task taskUpdated = taskService.createTask(task);
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
        return mapperToDto.mapToDto(taskService.deleteTasksById(id));
    }
}
