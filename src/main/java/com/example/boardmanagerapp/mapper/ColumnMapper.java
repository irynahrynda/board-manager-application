package com.example.boardmanagerapp.mapper;

import com.example.boardmanagerapp.dto.request.ColumnRequestDto;
import com.example.boardmanagerapp.dto.response.ColumnResponseDto;
import com.example.boardmanagerapp.model.Columnn;
import com.example.boardmanagerapp.model.Task;
import com.example.boardmanagerapp.service.BoardService;
import com.example.boardmanagerapp.service.TaskService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ColumnMapper implements MapperToModel<ColumnRequestDto, Columnn>,
        MapperToDto<ColumnResponseDto, Columnn> {

    private final BoardService boardService;
    private final TaskService taskService;

    public ColumnMapper(BoardService boardService, TaskService taskService) {
        this.boardService = boardService;
        this.taskService = taskService;
    }

    @Override
    public Columnn mapToModel(ColumnRequestDto columnRequestDto) {
        Columnn columnn = new Columnn();
        columnn.setName(columnRequestDto.getName());
        return columnn;
    }

    @Override
    public ColumnResponseDto mapToDto(Columnn columnn) {
        ColumnResponseDto columnResponseDto = new ColumnResponseDto();
        columnResponseDto.setId((columnn.getId()));
        columnResponseDto.setName(columnn.getName());
        if (columnn.getBoard() != null) {
            columnResponseDto.setBoardId(columnn.getBoard().getId());
        }

        if(columnn.getTasks() != null) {
            columnResponseDto.setTasksIds(columnn.getTasks()
                    .stream()
                    .map(Task::getId)
                    .collect(Collectors.toList()));
        }
        return columnResponseDto;
    }
}
