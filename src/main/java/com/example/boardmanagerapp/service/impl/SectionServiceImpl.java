package com.example.boardmanagerapp.service.impl;

import com.example.boardmanagerapp.dto.request.BoardRequestDto;
import com.example.boardmanagerapp.dto.request.SectionRequestDto;
import com.example.boardmanagerapp.dto.response.SectionResponseDto;
import com.example.boardmanagerapp.dto.response.SectionResponseDtoWithoutRelations;
import com.example.boardmanagerapp.mapper.MapperToDto;
import com.example.boardmanagerapp.mapper.MapperToModel;
import com.example.boardmanagerapp.model.Board;
import com.example.boardmanagerapp.model.Section;
import com.example.boardmanagerapp.repository.BoardRepository;
import com.example.boardmanagerapp.repository.SectionRepository;
import com.example.boardmanagerapp.service.SectionService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final BoardRepository boardRepository;
    private final MapperToModel<SectionRequestDto, Section> sectionMapperToModel;
    private final MapperToDto<SectionResponseDto, Section> sectionMapperToDto;
    private final MapperToDto<SectionResponseDtoWithoutRelations, Section> sectionMapperToDtoWithoutRelations;

    public SectionServiceImpl(SectionRepository sectionRepository, BoardRepository boardRepository, MapperToModel<SectionRequestDto, Section> sectionMapperToModel, MapperToDto<SectionResponseDto, Section> sectionMapperToDto, MapperToDto<SectionResponseDtoWithoutRelations, Section> sectionMapperToDtoWithoutRelations) {
        this.sectionRepository = sectionRepository;
        this.boardRepository = boardRepository;
        this.sectionMapperToModel = sectionMapperToModel;
        this.sectionMapperToDto = sectionMapperToDto;
        this.sectionMapperToDtoWithoutRelations = sectionMapperToDtoWithoutRelations;
    }

    @Override
    public SectionResponseDto createSection(SectionRequestDto sectionRequestDto) {
        return sectionMapperToDto.mapToDto(sectionRepository.save(sectionMapperToModel.mapToModel(sectionRequestDto)));
    }

    @Override
    public SectionResponseDto getSectionById(Long id) {
        return sectionMapperToDto.mapToDto(sectionRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get section by id " + id)));
    }

    @Override
    public List<SectionResponseDto> getAllSections() {
        return sectionRepository.findAll().stream()
                .map(sectionMapperToDto::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SectionResponseDto updateSectionById(Long id, SectionRequestDto sectionRequestDto) {
        Section sectionToUpdate = sectionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't get section by id: " + id));
        sectionToUpdate.setName(sectionRequestDto.getName());
        return sectionMapperToDto.mapToDto(sectionRepository.save(sectionToUpdate));
    }

    @Override
    public SectionResponseDtoWithoutRelations deleteSectionById(Long id) {
        Section sectionToDelete = sectionRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't delete section by id " + id));
        sectionRepository.deleteById(id);
        return sectionMapperToDtoWithoutRelations.mapToDto(sectionToDelete);
    }

    @Override
    public SectionResponseDto getSectionByName(String name) {
        return sectionMapperToDto.mapToDto(sectionRepository.getSectionByName(name));
    }

    @Override
    public SectionResponseDto setSectionToBoard(Long id, BoardRequestDto boardRequestDto) {
        Section section = sectionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't get section by id: " + id));
        Board board = boardRepository.getBoardByName(boardRequestDto.getName());
        List<Section> sections = board.getSections();
        List<Board> boards = section.getBoards();
        Section updatedSection = new Section();

        if (section != null && board != null) {
            boards.add(board);
            section.setBoards(boards);
            updatedSection = sectionRepository.save(section);
            sections.add(section);
            boardRepository.save(board);
        }

        return sectionMapperToDto.mapToDto(section);
    }
}
