package com.example.boardmanagerapp.controller;

import com.example.boardmanagerapp.dto.request.BoardRequestDto;
import com.example.boardmanagerapp.dto.request.SectionRequestDto;
import com.example.boardmanagerapp.dto.response.SectionResponseDto;
import com.example.boardmanagerapp.dto.response.SectionResponseDtoWithoutRelations;
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
@RequestMapping("/sections")
public class SectionController {
    private final SectionService sectionService;
    private final MapperToModel<SectionRequestDto, Section> mapperToModel;
    private final MapperToDto<SectionResponseDto, Section> mapperToDto;

    private final MapperToDto<SectionResponseDtoWithoutRelations, Section> mapperToDtoWithoutRelations;

    private final BoardService boardService;

    public SectionController(SectionService sectionService,
                             MapperToModel<SectionRequestDto,
                                     Section> mapperToModel,
                             MapperToDto<SectionResponseDto,
                                     Section> mapperToDto,
                             MapperToDto<SectionResponseDtoWithoutRelations,
                                     Section> mapperToDtoWithoutRelations, BoardService boardService) {
        this.sectionService = sectionService;
        this.mapperToModel = mapperToModel;
        this.mapperToDto = mapperToDto;
        this.mapperToDtoWithoutRelations = mapperToDtoWithoutRelations;
        this.boardService = boardService;
    }


    @PostMapping
    public SectionResponseDto createSection(@RequestBody SectionRequestDto sectionRequestDto) {
        return mapperToDto.mapToDto(sectionService.createSection(
                mapperToModel.mapToModel(sectionRequestDto)));
    }

    @GetMapping("/{id}")
    public SectionResponseDto getSectionById(@PathVariable Long id) {
        return mapperToDto.mapToDto(sectionService.getSectionById(id));
    }

    @GetMapping
    public List<SectionResponseDto> getAllSections() {
        return sectionService.getAllSections()
                .stream()
                .map(mapperToDto::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public SectionResponseDto updateColumn(@PathVariable Long id,
                                           @RequestBody SectionRequestDto sectionRequestDto) {
        Section section = sectionService.getSectionById(id);
        section.setName(sectionRequestDto.getName());
        sectionService.createSection(section);
        return mapperToDto.mapToDto(section);
    }

    @DeleteMapping("/{id}")
    public SectionResponseDtoWithoutRelations deleteSectionById(@PathVariable Long id) {
        Section section = sectionService.getSectionById(id);
        sectionService.deleteSectionById(id);
        return mapperToDtoWithoutRelations.mapToDto(section);
    }

    @GetMapping("/by-name")
    public SectionResponseDto getSectionByName(@RequestParam String name) {
        return mapperToDto.mapToDto(sectionService.getSectionByName(name));
    }

    @PutMapping("/{id}/set-board")
    public SectionResponseDto setSectionToBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) {
        Section section = sectionService.getSectionById(id);
        Board board = boardService.getBoardByName(boardRequestDto.getName());
        List<Section> sections = board.getSections();
        List<Board> boards = section.getBoards();
        Section updatedSection = new Section();


        if (section != null && board != null) {
            boards.add(board);
            section.setBoards(boards);
            updatedSection = sectionService.createSection(section);
            sections.add(section);
            boardService.createBoard(board);
        }

        return mapperToDto.mapToDto(section);
    }
}
