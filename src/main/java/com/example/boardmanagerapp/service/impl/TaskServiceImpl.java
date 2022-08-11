package com.example.boardmanagerapp.service.impl;

import com.example.boardmanagerapp.dto.request.SectionRequestDto;
import com.example.boardmanagerapp.dto.request.TaskRequestDto;
import com.example.boardmanagerapp.dto.response.TaskResponseDto;
import com.example.boardmanagerapp.mapper.MapperToDto;
import com.example.boardmanagerapp.mapper.MapperToModel;
import com.example.boardmanagerapp.model.Section;
import com.example.boardmanagerapp.model.Task;
import com.example.boardmanagerapp.repository.SectionRepository;
import com.example.boardmanagerapp.repository.TaskRepository;
import com.example.boardmanagerapp.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final SectionRepository sectionRepository;
    private final MapperToModel<TaskRequestDto, Task> taskMapperToModel;
    private final MapperToDto<TaskResponseDto, Task> taskMapperToDto;

    public TaskServiceImpl(TaskRepository taskRepository, SectionRepository sectionRepository, MapperToModel<TaskRequestDto, Task> taskMapperToModel, MapperToDto<TaskResponseDto, Task> taskMapperToDto) {
        this.taskRepository = taskRepository;
        this.sectionRepository = sectionRepository;
        this.taskMapperToModel = taskMapperToModel;
        this.taskMapperToDto = taskMapperToDto;
    }

    @Override
    public TaskResponseDto createTask(Long id, TaskRequestDto taskRequestDto) {
        Task task = taskMapperToModel.mapToModel(taskRequestDto);
        Section section = sectionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't get section by id: " + id));
        task.setSection(section);
        Task taskWithColumns = taskRepository.save(task);
        if (taskWithColumns != null) {
            List<Task> tasks = section.getTasks();
            tasks.add(taskWithColumns);
            section.setTasks(tasks);
            sectionRepository.save(section);
        }

        return taskMapperToDto.mapToDto(taskWithColumns);
    }

    @Override
    public TaskResponseDto getTaskById(Long id) {
        return taskMapperToDto.mapToDto(taskRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get task by id " + id)));
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapperToDto::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDto deleteTasksById(Long id) {
        Task taskToDelete = taskRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't delete task by id " + id));
        taskRepository.deleteById(id);
        return taskMapperToDto.mapToDto(taskToDelete);
    }

    @Override
    public TaskResponseDto updateTaskById(Long id, TaskRequestDto taskRequestDto) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get task by id " + id));

        if (taskRequestDto.getTitle() != null) {
            task.setTitle(taskRequestDto.getTitle());
        }

        if (taskRequestDto.getDescription() != null) {
            task.setDescription(taskRequestDto.getDescription());
        }

        Task updatedTask = taskRepository.save(task);
        return taskMapperToDto.mapToDto(updatedTask);
    }

    @Override
    public TaskResponseDto updateStatus(Long id, SectionRequestDto sectionRequestDto) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get task by id " + id));
        Section section = sectionRepository.getSectionByName(sectionRequestDto.getName());

        if (task != null && section != null) {
            task.setSection(section);
        }

        Task taskUpdated = taskRepository.save(task);
        return taskMapperToDto.mapToDto(taskUpdated);
    }
}
