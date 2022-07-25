package com.example.boardmanagerapp.mapper;

import com.example.boardmanagerapp.dto.request.TaskRequestDto;
import com.example.boardmanagerapp.dto.response.TaskResponseDto;
import com.example.boardmanagerapp.model.Task;
import com.example.boardmanagerapp.service.ColumnService;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper implements MapperToModel<TaskRequestDto, Task>,
        MapperToDto<TaskResponseDto, Task> {

    private final ColumnService columnService;

    public TaskMapper(ColumnService columnService) {
        this.columnService = columnService;
    }

    @Override
    public Task mapToModel(TaskRequestDto taskRequestDto) {
        Task task = new Task();
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
       // task.setColumn(columnService.getColumnById(taskRequestDto.g);
        return task;
    }

    @Override
    public TaskResponseDto mapToDto(Task task) {
        TaskResponseDto taskResponseDto = new TaskResponseDto();
        taskResponseDto.setId((task.getId()));
        taskResponseDto.setTitle(task.getTitle());
        taskResponseDto.setDescription(task.getDescription());
        taskResponseDto.setColumnId(task.getColumn().getId());
        return taskResponseDto;
    }
}
