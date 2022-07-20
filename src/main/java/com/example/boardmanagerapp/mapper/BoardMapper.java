package com.example.boardmanagerapp.mapper;

import com.example.boardmanagerapp.dto.request.BoardRequestDto;
import com.example.boardmanagerapp.dto.response.BoardResponseDto;
import com.example.boardmanagerapp.model.Board;
import com.example.boardmanagerapp.model.Column;
import com.example.boardmanagerapp.service.ColumnService;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class BoardMapper implements RequestDtoMapper<BoardRequestDto, Board>,
        ResponseDtoMapper<BoardResponseDto, Board> {
    private final ColumnService columnService;

    public BoardMapper(ColumnService columnService) {
        this.columnService = columnService;
    }

    @Override
    public Board mapToModel(BoardRequestDto dto) {
        Board board = new Board();
        board.setName(dto.getName());
        board.setBackgroundImagePath(dto.getBackgroundImagePath());
        board.setColumns(dto.getColumnsIds().stream()
                .map(columnService::getColumnById)
                .collect(Collectors.toList()));
        return board;
    }

    @Override
    public BoardResponseDto mapToDto(Board model) {
        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setId(model.getId());
        boardResponseDto.setName(model.getName());
        boardResponseDto.setBackgroundImagePath(model.getBackgroundImagePath());
        boardResponseDto.setColumnsIds(model.getColumns()
                .stream()
                .map(Column::getId)
                .collect(Collectors.toList()));
        return boardResponseDto;
    }
}
