package com.example.boardmanagerapp.service.impl;

import com.example.boardmanagerapp.dto.request.BoardRequestDto;
import com.example.boardmanagerapp.dto.request.SectionRequestDto;
import com.example.boardmanagerapp.dto.response.BoardResponseDto;
import com.example.boardmanagerapp.dto.response.BoardResponseDtoWithSections;
import com.example.boardmanagerapp.mapper.MapperToDto;
import com.example.boardmanagerapp.mapper.MapperToModel;
import com.example.boardmanagerapp.model.Board;
import com.example.boardmanagerapp.model.Section;
import com.example.boardmanagerapp.repository.BoardRepository;
import com.example.boardmanagerapp.repository.SectionRepository;
import com.example.boardmanagerapp.service.BoardService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final SectionRepository sectionRepository;
    private final MapperToModel<BoardRequestDto, Board> boardMapperToModel;
    private final MapperToDto<BoardResponseDto, Board> boardMapperToDto;
    private final MapperToDto<BoardResponseDtoWithSections, Board> boardMapperWithSection;

    public BoardServiceImpl(BoardRepository boardRepository, SectionRepository sectionRepository, MapperToModel<BoardRequestDto, Board> boardMapperToModel, MapperToDto<BoardResponseDto, Board> boardMapperToDto, MapperToDto<BoardResponseDtoWithSections, Board> boardMapperWithSection) {
        this.boardRepository = boardRepository;
        this.sectionRepository = sectionRepository;
        this.boardMapperToModel = boardMapperToModel;
        this.boardMapperToDto = boardMapperToDto;
        this.boardMapperWithSection = boardMapperWithSection;
    }

    @Override
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto) {
        return boardMapperToDto.mapToDto(boardRepository.save(boardMapperToModel.mapToModel(boardRequestDto)));
    }

    @Override
    public BoardResponseDto getBoardById(Long id) {
        return boardMapperToDto.mapToDto(boardRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get board by id " + id)));
    }

    @Override
    public List<BoardResponseDto> getAllBoards(PageRequest pageRequest) {
        return boardRepository.findAll(pageRequest).stream()
                .map(boardMapperToDto::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BoardResponseDto deleteBoardById(Long id) {
        Board boardToDelete = boardRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't delete board by id " + id));
        boardRepository.deleteById(id);
        return boardMapperToDto.mapToDto(boardToDelete);
    }

    @Override
    public BoardResponseDto renameBoardById(Long id, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't get board by id: " + id));
        board.setName(boardRequestDto.getName());
        Board updateBoard = boardRepository.save(board);
        return boardMapperToDto.mapToDto(updateBoard);
    }

    @Override
    public BoardResponseDto addImagePathToBoard(Long id, String path) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't get board by id: " + id));
        board.setBackgroundImagePath(path);
        Board updateBoard = boardRepository.save(board);
        return boardMapperToDto.mapToDto(updateBoard);
    }

    @Override
    public BoardResponseDtoWithSections getFullBoardById(Long id) {
        return boardMapperWithSection.mapToDto(boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't get board by id: " + id)));
    }

    @Override
    public String removeSectionFromBoard(Long id, SectionRequestDto sectionRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't get board by id: " + id));
        List<Section> sections = board.getSections();
        Section section = sectionRepository.getSectionByName(sectionRequestDto.getName());
        List<Board> boards = section.getBoards();
        Board updatedBoard = new Board();
        Section updatedSection = new Section();

        if (board != null && section != null) {
            sections.remove(section);
            board.setSections(sections);
            updatedBoard = boardRepository.save(board);
            boards.remove(board);
            updatedSection = sectionRepository.save(section);
        }

        if (updatedBoard == null) {
            throw new RuntimeException("Can't remove section from board with id: " + id);
        }

        if (updatedSection == null) {
            throw new RuntimeException("Can't update section after removing board with id: " + id);
        }
        return "Successfully deleted!";
    }
}
