package com.example.boardmanagerapp.mapper;

import com.example.boardmanagerapp.dto.request.TaskRequestDto;
import com.example.boardmanagerapp.dto.response.TaskResponseDto;
import com.example.boardmanagerapp.model.Task;
import com.example.boardmanagerapp.service.SectionService;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper implements MapperToModel<TaskRequestDto, Task>,
        MapperToDto<TaskResponseDto, Task> {

    @Override
    public Task mapToModel(TaskRequestDto taskRequestDto) {
        Task task = new Task();
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        return task;
    }

    @Override
    public TaskResponseDto mapToDto(Task task) {
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setId((task.getId()));
        taskResponseDto.setTitle(task.getTitle());
        taskResponseDto.setDescription(task.getDescription());
        taskResponseDto.setSectionId(task.getSection().getId());
        return taskResponseDto;
    }
}
