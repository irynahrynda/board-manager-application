package com.example.boardmanagerapp.controller;

import com.example.boardmanagerapp.dto.request.BoardRequestDto;
import com.example.boardmanagerapp.dto.request.SectionRequestDto;
import com.example.boardmanagerapp.dto.response.BoardResponseDto;
import com.example.boardmanagerapp.dto.response.BoardResponseDtoWithSections;
import com.example.boardmanagerapp.mapper.MapperToDto;
import com.example.boardmanagerapp.mapper.MapperToModel;
import com.example.boardmanagerapp.model.Board;
import com.example.boardmanagerapp.model.Section;
import com.example.boardmanagerapp.service.BoardService;
import com.example.boardmanagerapp.service.SectionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    private final MapperToModel<BoardRequestDto, Board> mapperToModel;
    private final MapperToDto<BoardResponseDto, Board> mapperToDto;
    private final SectionService sectionService;

    private final MapperToDto<BoardResponseDtoWithSections, Board> boardResponseDtoMapperWithSection;

    public BoardController(BoardService boardService,
                           MapperToModel<BoardRequestDto, Board> mapperToModel,
                           MapperToDto<BoardResponseDto, Board> mapperToDto,
                           SectionService sectionService,
                           MapperToDto<BoardResponseDtoWithSections, Board> boardResponseDtoMapperWithSection) {
        this.boardService = boardService;
        this.mapperToModel = mapperToModel;
        this.mapperToDto = mapperToDto;
        this.sectionService = sectionService;
        this.boardResponseDtoMapperWithSection = boardResponseDtoMapperWithSection;
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

    @PutMapping("/change/{id}")
    public BoardResponseDto addImagePathToBoard(@PathVariable Long id, @RequestBody String path) {
        Board board = boardService.getBoardById(id);
        board.setBackgroundImagePath(path);
        Board updateBoard = boardService.createBoard(board);
        return mapperToDto.mapToDto(updateBoard);
    }


    @DeleteMapping("/{id}")
//    @ApiOperation(value = "Delete board by id")
    public BoardResponseDto deleteBoardById(@PathVariable Long id) {
        BoardResponseDto boardResponseDto = mapperToDto.mapToDto(boardService.deleteBoardById(id));
        return boardResponseDto;
    }

    @GetMapping("/{id}/read-full")
    public BoardResponseDtoWithSections getFullBoardById(@PathVariable Long id) {
        return boardResponseDtoMapperWithSection.mapToDto(boardService.getBoardById(id));
    }

    @PutMapping("/{id}/remove-section")
    public String removeSectionFromBoard(@PathVariable Long id, @RequestBody SectionRequestDto sectionRequestDto) {
        Board board = boardService.getBoardById(id);
        List<Section> sections = board.getSections();
        Section section = sectionService.getSectionByName(sectionRequestDto.getName());
        List<Board> boards = section.getBoards();
        Board updatedBoard = new Board();
        Section updatedSection = new Section();
        if (board != null && section != null) {
            sections.remove(section);
            board.setSections(sections);
            updatedBoard = boardService.createBoard(board);
            boards.remove(board);
          updatedSection = sectionService.createSection(section);
        }

        if (updatedBoard == null) {
            throw new RuntimeException("Can't remove section from board with id: " + id );
        }

        if (updatedSection == null) {
            throw new RuntimeException("Can't update section after removing board with id: " + id);
        }
        return "Successfully deleted!";
    }
}


