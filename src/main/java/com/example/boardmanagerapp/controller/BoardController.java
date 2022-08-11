package com.example.boardmanagerapp.controller;

import com.example.boardmanagerapp.dto.request.BoardRequestDto;
import com.example.boardmanagerapp.dto.request.SectionRequestDto;
import com.example.boardmanagerapp.dto.response.BoardResponseDto;
import com.example.boardmanagerapp.dto.response.BoardResponseDtoWithSections;
import com.example.boardmanagerapp.service.BoardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    @ApiOperation(value = "Create new board")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto boardRequestDto) {
        return boardService.createBoard(boardRequestDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get board by id")
    public BoardResponseDto getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id);
    }

    @GetMapping
    @ApiOperation(value = "Get all boards with pagination")
    public List<BoardResponseDto> getAllBoards(@RequestParam(defaultValue = "10")
                                               @ApiParam(value = "Default value " + "is `10`") Integer count,
                                               @RequestParam(defaultValue = "0")
                                               @ApiParam(value = "Default value " + "is `0`") Integer page) {
        PageRequest pageRequest = PageRequest.of(page, count);
        return boardService.getAllBoards(pageRequest);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete board by id")
    public BoardResponseDto deleteBoardById(@PathVariable Long id) {
        return boardService.deleteBoardById(id);
    }

    @PutMapping("/rename/{id}")
    @ApiOperation(value = "Rename board by id")
    public BoardResponseDto renameBoardById(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.renameBoardById(id, boardRequestDto);
    }

    @PutMapping("/change/{id}")
    @ApiOperation(value = "Update board after adding image")
    public BoardResponseDto addImagePathToBoard(@PathVariable Long id, @RequestBody String path) {
        return boardService.addImagePathToBoard(id, path);
    }

    @GetMapping("/{id}/read-full")
    @ApiOperation(value = "Get all boards with full info")
    public BoardResponseDtoWithSections getFullBoardById(@PathVariable Long id) {
        return boardService.getFullBoardById(id);
    }

    @PutMapping("/{id}/remove-section")
    @ApiOperation(value = "Remove section from board")
    public String removeSectionFromBoard(@PathVariable Long id, @RequestBody SectionRequestDto sectionRequestDto) {
        return boardService.removeSectionFromBoard(id, sectionRequestDto);
    }
}
