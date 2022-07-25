package com.example.boardmanagerapp.mapper;

import com.example.boardmanagerapp.dto.response.ColumnResponseDtoWithTasks;
import com.example.boardmanagerapp.dto.response.TaskResponseDto;
import com.example.boardmanagerapp.model.Board;
import com.example.boardmanagerapp.model.Columnn;
import com.example.boardmanagerapp.model.Task;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ColumnMapperWithTasks implements MapperToDto<ColumnResponseDtoWithTasks, Columnn> {
    private final MapperToDto<TaskResponseDto, Task> taskMapperToDto;

    public ColumnMapperWithTasks(MapperToDto<TaskResponseDto, Task> taskMapperToDto) {
        this.taskMapperToDto = taskMapperToDto;
    }


    @Override
    public ColumnResponseDtoWithTasks mapToDto(Columnn columnn) {
        ColumnResponseDtoWithTasks columnResponseDtoWithTasks = new ColumnResponseDtoWithTasks();
        columnResponseDtoWithTasks.setId(columnn.getId());
        columnResponseDtoWithTasks.setName(columnn.getName());

        if (columnn.getBoards() != null && !columnn.getBoards().isEmpty()) {
            columnResponseDtoWithTasks.setBoardIds(columnn.getBoards().stream()
                    .map(Board::getId)
                    .collect(Collectors.toList()));
        }

        if (columnn.getTasks() != null) {
            columnResponseDtoWithTasks.setTasks(columnn.getTasks()
                    .stream()
                    .map(taskMapperToDto::mapToDto)
                    .collect(Collectors.toList()));
        }

        return columnResponseDtoWithTasks;
    }
}
