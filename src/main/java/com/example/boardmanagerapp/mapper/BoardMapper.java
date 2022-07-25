package com.example.boardmanagerapp.mapper;

import com.example.boardmanagerapp.dto.request.BoardRequestDto;
import com.example.boardmanagerapp.dto.response.BoardResponseDto;
import com.example.boardmanagerapp.model.Board;
import com.example.boardmanagerapp.model.Columnn;
import com.example.boardmanagerapp.service.ColumnService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BoardMapper implements MapperToModel<BoardRequestDto, Board>,
        MapperToDto<BoardResponseDto, Board> {
    private final ColumnService columnService;

    public BoardMapper(ColumnService columnService) {
        this.columnService = columnService;
    }

    @Override
    public Board mapToModel(BoardRequestDto boardRequestDto) {
        Board board = new Board();
        board.setName(boardRequestDto.getName());
//        board.setBackgroundImagePath(boardRequestDto.getBackgroundImagePath());
//        board.setColumns(dto.getColumnsIds().stream()
//                .map(columnService::getColumnById)
//                .collect(Collectors.toList()));
        return board;
    }

    @Override
    public BoardResponseDto mapToDto(Board board) {
        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setId(board.getId());
        boardResponseDto.setName(board.getName());
        boardResponseDto.setBackgroundImagePath(board.getBackgroundImagePath());
        if (board.getColumns() != null) {
            boardResponseDto.setColumnsIds(board.getColumns()
                    .stream()
                    .map(Columnn::getId)
                    .collect(Collectors.toList()));
        }
        return boardResponseDto;
    }
}
