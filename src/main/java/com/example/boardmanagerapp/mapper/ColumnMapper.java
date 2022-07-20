package com.example.boardmanagerapp.mapper;

import com.example.boardmanagerapp.dto.request.ColumnRequestDto;
import com.example.boardmanagerapp.dto.response.ColumnResponseDto;
import com.example.boardmanagerapp.model.Column;
import com.example.boardmanagerapp.model.Task;
import com.example.boardmanagerapp.service.BoardService;
import com.example.boardmanagerapp.service.TaskService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ColumnMapper implements RequestDtoMapper<ColumnRequestDto, Column>,
        ResponseDtoMapper<ColumnResponseDto, Column> {

    private final BoardService boardService;
    private final TaskService taskService;

    public ColumnMapper(BoardService boardService, TaskService taskService) {
        this.boardService = boardService;
        this.taskService = taskService;
    }

    @Override
    public Column mapToModel(ColumnRequestDto dto) {
        Column column = new Column();
        column.setName(dto.getName());
        column.setBoard(boardService.getBoardById(dto.getBoardId()));
        column.setTasks(dto.getTasksIds().stream()
                .map(taskService::getTaskById)
                .collect(Collectors.toList()));
        return column;
    }

    @Override
    public ColumnResponseDto mapToDto(Column model) {
        ColumnResponseDto columnResponseDto = new ColumnResponseDto();
        columnResponseDto.setId((model.getId()));
        columnResponseDto.setName(model.getName());
        columnResponseDto.setBoardId(model.getBoard().getId());
        columnResponseDto.setTasksIds(model.getTasks()
                .stream()
                .map(Task::getId)
                .collect(Collectors.toList()));
        return columnResponseDto;

    }
}
