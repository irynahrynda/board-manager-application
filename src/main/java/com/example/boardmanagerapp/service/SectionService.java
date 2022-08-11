package com.example.boardmanagerapp.service;

import com.example.boardmanagerapp.dto.request.BoardRequestDto;
import com.example.boardmanagerapp.dto.request.SectionRequestDto;
import com.example.boardmanagerapp.dto.response.SectionResponseDto;
import com.example.boardmanagerapp.dto.response.SectionResponseDtoWithoutRelations;

import java.util.List;

public interface SectionService {
    SectionResponseDto createSection(SectionRequestDto sectionRequestDto);

    SectionResponseDto getSectionById(Long id);

    List<SectionResponseDto> getAllSections();

    SectionResponseDto updateSectionById(Long id, SectionRequestDto sectionRequestDto);

    SectionResponseDtoWithoutRelations deleteSectionById(Long id);

    SectionResponseDto getSectionByName(String name);

    SectionResponseDto setSectionToBoard(Long id, BoardRequestDto boardRequestDto);
}
