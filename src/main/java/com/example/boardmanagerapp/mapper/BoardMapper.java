package com.example.boardmanagerapp.mapper;

import com.example.boardmanagerapp.dto.request.BoardRequestDto;
import com.example.boardmanagerapp.dto.response.BoardResponseDto;
import com.example.boardmanagerapp.model.Board;
import com.example.boardmanagerapp.model.Section;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BoardMapper implements MapperToModel<BoardRequestDto, Board>,
        MapperToDto<BoardResponseDto, Board> {

    @Override
    public Board mapToModel(BoardRequestDto boardRequestDto) {
        Board board = new Board();
        board.setName(boardRequestDto.getName());
//        board.setBackgroundImagePath(boardRequestDto.getBackgroundImagePath());
        return board;
    }

    @Override
    public BoardResponseDto mapToDto(Board board) {
        BoardResponseDto boardResponseDto = new BoardResponseDto();
        boardResponseDto.setId(board.getId());
        boardResponseDto.setName(board.getName());
        boardResponseDto.setBackgroundImagePath(board.getBackgroundImagePath());
        if (board.getSections() != null) {
            boardResponseDto.setSectionIds(board.getSections()
                    .stream()
                    .map(Section::getId)
                    .collect(Collectors.toList()));
        }
        return boardResponseDto;
    }
}
