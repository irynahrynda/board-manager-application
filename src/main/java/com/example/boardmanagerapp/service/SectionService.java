package com.example.boardmanagerapp.service;

import com.example.boardmanagerapp.model.Section;

import java.util.List;

public interface SectionService {
    Section createSection(Section section);

    Section getSectionById(Long id);

    List<Section> getAllSections();

    Section deleteSectionById(Long id);

    Section getSectionByName(String name);
}
