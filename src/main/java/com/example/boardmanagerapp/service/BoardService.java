package com.example.boardmanagerapp.service;

import com.example.boardmanagerapp.dto.request.BoardRequestDto;
import com.example.boardmanagerapp.dto.request.SectionRequestDto;
import com.example.boardmanagerapp.dto.response.BoardResponseDto;
import com.example.boardmanagerapp.dto.response.BoardResponseDtoWithSections;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface BoardService {
    BoardResponseDto createBoard(BoardRequestDto boardRequestDto);

    BoardResponseDto getBoardById(Long id);

    List<BoardResponseDto> getAllBoards(PageRequest pageRequest);

    BoardResponseDto deleteBoardById(Long id);

    BoardResponseDto renameBoardById(Long id, BoardRequestDto boardRequestDto);

    BoardResponseDto addImagePathToBoard(Long id, String path);

    BoardResponseDtoWithSections getFullBoardById(Long id);

    String removeSectionFromBoard(Long id, SectionRequestDto sectionRequestDto);
}
