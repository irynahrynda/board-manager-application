package com.example.boardmanagerapp.controller;

import com.example.boardmanagerapp.dto.request.BoardRequestDto;
import com.example.boardmanagerapp.dto.response.BoardResponseDto;
import com.example.boardmanagerapp.mapper.MapperToDto;
import com.example.boardmanagerapp.mapper.MapperToModel;
import com.example.boardmanagerapp.model.Board;
import com.example.boardmanagerapp.service.BoardService;
import com.example.boardmanagerapp.service.ColumnService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    private final MapperToModel<BoardRequestDto, Board> mapperToModel;
    private final MapperToDto<BoardResponseDto, Board> mapperToDto;
    private final ColumnService columnService;

    public BoardController(BoardService boardService, MapperToModel<BoardRequestDto, Board> mapperToModel, MapperToDto<BoardResponseDto, Board> mapperToDto, ColumnService columnService) {
        this.boardService = boardService;
        this.mapperToModel = mapperToModel;
        this.mapperToDto = mapperToDto;
        this.columnService = columnService;
    }


    @PostMapping
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto boardRequestDto) {
        return mapperToDto.mapToDto(boardService.createBoard(
                mapperToModel.mapToModel(boardRequestDto)));
    }

    @GetMapping
    public List<BoardResponseDto> getAllBoards() {
        return boardService.getAllBoards().stream()
                .map(mapperToDto::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BoardResponseDto getBoardById(@PathVariable Long id) {
        return mapperToDto.mapToDto(boardService.getBoardById(id));
    }

    @PutMapping("/rename/{id}")
    public BoardResponseDto renameBoardById(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) {
        Board board = boardService.getBoardById(id);
        board.setName(boardRequestDto.getName());
        Board updateBoard = boardService.createBoard(board);
        return mapperToDto.mapToDto(updateBoard);
    }

    @PutMapping("/change-image/{id}")
    public BoardResponseDto addColumnToBoard(@PathVariable Long id, @RequestBody String path) {
        Board board = boardService.getBoardById(id);
        board.setBackgroundImagePath(path);
        Board updateBoard = boardService.createBoard(board);
        return mapperToDto.mapToDto(updateBoard);
    }  //test


    @DeleteMapping("/{id}")
//    @ApiOperation(value = "Delete board by id")
    public BoardResponseDto deleteBoardById(@PathVariable Long id) {
        BoardResponseDto boardResponseDto = mapperToDto.mapToDto(boardService.deleteBoardById(id));
        return boardResponseDto;
    }
}
