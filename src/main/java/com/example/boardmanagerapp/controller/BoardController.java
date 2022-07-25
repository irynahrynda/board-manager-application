package com.example.boardmanagerapp.controller;

import com.example.boardmanagerapp.dto.request.BoardRequestDto;
import com.example.boardmanagerapp.dto.request.SectionRequestDto;
import com.example.boardmanagerapp.dto.response.BoardResponseDto;
import com.example.boardmanagerapp.dto.response.BoardResponseDtoWithSections;
import com.example.boardmanagerapp.service.BoardService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto boardRequestDto) {
        return boardService.createBoard(boardRequestDto);
    }

    @GetMapping("/{id}")
    public BoardResponseDto getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id);
    }

    @GetMapping
    public List<BoardResponseDto> getAllBoards() {
        return boardService.getAllBoards();
    }

    @DeleteMapping("/{id}")
    public BoardResponseDto deleteBoardById(@PathVariable Long id) {
        return boardService.deleteBoardById(id);
    }

    @PutMapping("/rename/{id}")
    public BoardResponseDto renameBoardById(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.renameBoardById(id, boardRequestDto);
    }

    @PutMapping("/change/{id}")
    public BoardResponseDto addImagePathToBoard(@PathVariable Long id, @RequestBody String path) {
        return boardService.addImagePathToBoard(id, path);
    }

    @GetMapping("/{id}/read-full")
    public BoardResponseDtoWithSections getFullBoardById(@PathVariable Long id) {
        return boardService.getFullBoardById(id);
    }

    @PutMapping("/{id}/remove-section")
    public String removeSectionFromBoard(@PathVariable Long id, @RequestBody SectionRequestDto sectionRequestDto) {
        return boardService.removeSectionFromBoard(id, sectionRequestDto);
    }
}
