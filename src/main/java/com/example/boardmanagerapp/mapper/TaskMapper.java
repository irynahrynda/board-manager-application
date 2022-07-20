package com.example.boardmanagerapp.mapper;

import com.example.boardmanagerapp.dto.request.TaskRequestDto;
import com.example.boardmanagerapp.dto.response.TaskResponseDto;
import com.example.boardmanagerapp.model.Task;
import com.example.boardmanagerapp.service.ColumnService;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper implements RequestDtoMapper<TaskRequestDto, Task>,
        ResponseDtoMapper<TaskResponseDto, Task> {

    private final ColumnService columnService;

    public TaskMapper(ColumnService columnService) {
        this.columnService = columnService;
    }

    @Override
    public Task mapToModel(TaskRequestDto dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setColumn(columnService.getColumnById(dto.getColumnId()));
        return task;
    }

    @Override
    public TaskResponseDto mapToDto(Task model) {
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setId((model.getId()));
        taskResponseDto.setTitle(model.getTitle());
        taskResponseDto.setDescription(model.getDescription());
        taskResponseDto.setColumnId(model.getColumn().getId());
        return taskResponseDto;
    }
}
