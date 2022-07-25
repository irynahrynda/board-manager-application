package com.example.boardmanagerapp.service.impl;

import com.example.boardmanagerapp.model.Section;
import com.example.boardmanagerapp.repository.SectionRepository;
import com.example.boardmanagerapp.service.SectionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;

    public SectionServiceImpl(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public Section createSection(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public Section getSectionById(Long id) {
        return sectionRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get section by id " + id));
    }

    @Override
    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    public Section deleteSectionById(Long id) {
        Section sectionToDelete = sectionRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't delete section by id " + id));
        sectionRepository.deleteById(id);
        return sectionToDelete;
    }

    @Override
    public Section getSectionByName(String name) {
      return sectionRepository.getSectionByName(name);
    }
}
