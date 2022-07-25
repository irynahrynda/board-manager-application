package com.example.boardmanagerapp.mapper;

import com.example.boardmanagerapp.dto.response.BoardResponseDtoWithColumns;
import com.example.boardmanagerapp.dto.response.ColumnResponseDtoWithTasks;
import com.example.boardmanagerapp.model.Board;
import com.example.boardmanagerapp.model.Columnn;
import com.example.boardmanagerapp.service.ColumnService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BoardMapperWithColumns implements MapperToDto<BoardResponseDtoWithColumns, Board> {
    private final ColumnService columnService;
    private final MapperToDto<ColumnResponseDtoWithTasks, Columnn> columnMapperWithTasks;

    public BoardMapperWithColumns(ColumnService columnService, MapperToDto<ColumnResponseDtoWithTasks, Columnn> columnMapperWithTasks) {
        this.columnService = columnService;
        this.columnMapperWithTasks = columnMapperWithTasks;
    }


    @Override
    public BoardResponseDtoWithColumns mapToDto(Board board) {
        BoardResponseDtoWithColumns boardResponseDtoWithColumns = new BoardResponseDtoWithColumns();
        boardResponseDtoWithColumns.setId(board.getId());
        boardResponseDtoWithColumns.setName(board.getName());
        boardResponseDtoWithColumns.setBackgroundImagePath(board.getBackgroundImagePath());
        if (board.getColumns() != null) {
            boardResponseDtoWithColumns.setColumnsWithTasks(board.getColumns().stream()
                    .map(columnMapperWithTasks::mapToDto)
                    .collect(Collectors.toList()));
        }
        return boardResponseDtoWithColumns;
    }
}
