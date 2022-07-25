package com.example.boardmanagerapp.repository;

import com.example.boardmanagerapp.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    Section getSectionByName(String name);
}
