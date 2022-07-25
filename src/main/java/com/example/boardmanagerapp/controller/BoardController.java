package com.example.boardmanagerapp.controller;

import com.example.boardmanagerapp.dto.request.BoardRequestDto;
import com.example.boardmanagerapp.dto.request.ColumnRequestDto;
import com.example.boardmanagerapp.dto.response.BoardResponseDto;
import com.example.boardmanagerapp.dto.response.BoardResponseDtoWithColumns;
import com.example.boardmanagerapp.mapper.MapperToDto;
import com.example.boardmanagerapp.mapper.MapperToModel;
import com.example.boardmanagerapp.model.Board;
import com.example.boardmanagerapp.model.Columnn;
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

    private final MapperToDto<BoardResponseDtoWithColumns, Board> boardResponseDtoWithColumnsBoardMapperToDto;

    public BoardController(BoardService boardService, MapperToModel<BoardRequestDto, Board> mapperToModel, MapperToDto<BoardResponseDto, Board> mapperToDto, ColumnService columnService, MapperToDto<BoardResponseDtoWithColumns, Board> boardResponseDtoWithColumnsBoardMapperToDto) {
        this.boardService = boardService;
        this.mapperToModel = mapperToModel;
        this.mapperToDto = mapperToDto;
        this.columnService = columnService;
        this.boardResponseDtoWithColumnsBoardMapperToDto = boardResponseDtoWithColumnsBoardMapperToDto;
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

    @GetMapping("/{id}/read-full")
    public BoardResponseDtoWithColumns getFullBoardById(@PathVariable Long id) {
        return boardResponseDtoWithColumnsBoardMapperToDto.mapToDto(boardService.getBoardById(id));
    }

    @PutMapping("/{id}/remove-column")
    public String removeColumnFromBoard(@PathVariable Long id, @RequestBody ColumnRequestDto columnRequestDto) {
        Board board = boardService.getBoardById(id);
        List<Columnn> columnns = board.getColumns();
        Columnn columnn = columnService.getColumnByName(columnRequestDto.getName());
        List<Board> boards = columnn.getBoards();
        Board updatedBoard = new Board();
        Columnn updatedColumn = new Columnn();
        if (board != null && columnn != null) {
            columnns.remove(columnn);
            board.setColumns(columnns);
            updatedBoard = boardService.createBoard(board);
            boards.remove(board);
          updatedColumn = columnService.createColumn(columnn);
        }

        if (updatedBoard == null) {
            throw new RuntimeException("Can't remove column from board with id: " + id );
        }

        if (updatedColumn == null) {
            throw new RuntimeException("Can't update column after removing board with id: " + id);
        }
        return "Successfully deleted!";
    }
}


