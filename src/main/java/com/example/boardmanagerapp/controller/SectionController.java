package com.example.boardmanagerapp.controller;

import com.example.boardmanagerapp.dto.request.BoardRequestDto;
import com.example.boardmanagerapp.dto.request.SectionRequestDto;
import com.example.boardmanagerapp.dto.response.SectionResponseDto;
import com.example.boardmanagerapp.dto.response.SectionResponseDtoWithoutRelations;
import com.example.boardmanagerapp.service.SectionService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sections")
public class SectionController {
    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @PostMapping
    public SectionResponseDto createSection(@RequestBody SectionRequestDto sectionRequestDto) {
        return sectionService.createSection(sectionRequestDto);
    }

    @GetMapping("/{id}")
    public SectionResponseDto getSectionById(@PathVariable Long id) {
        return sectionService.getSectionById(id);
    }

    @GetMapping
    public List<SectionResponseDto> getAllSections() {
        return sectionService.getAllSections();
    }

    @PutMapping("/{id}")
    public SectionResponseDto updateColumn(@PathVariable Long id,
                                           @RequestBody SectionRequestDto sectionRequestDto) {
        return sectionService.updateSectionById(id, sectionRequestDto);
    }

    @DeleteMapping("/{id}")
    public SectionResponseDtoWithoutRelations deleteSectionById(@PathVariable Long id) {
        return sectionService.deleteSectionById(id);
    }

    @GetMapping("/by-name")
    public SectionResponseDto getSectionByName(@RequestParam String name) {
        return sectionService.getSectionByName(name);
    }

    @PutMapping("/{id}/set-to-board")
    public SectionResponseDto setSectionToBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) {
        return sectionService.setSectionToBoard(id, boardRequestDto);
    }
}
