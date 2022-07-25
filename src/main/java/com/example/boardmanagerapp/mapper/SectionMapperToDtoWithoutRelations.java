package com.example.boardmanagerapp.mapper;

import com.example.boardmanagerapp.dto.response.SectionResponseDtoWithoutRelations;
import com.example.boardmanagerapp.model.Section;
import org.springframework.stereotype.Component;

@Component
public class SectionMapperToDtoWithoutRelations implements MapperToDto<SectionResponseDtoWithoutRelations, Section> {
    @Override
    public SectionResponseDtoWithoutRelations mapToDto(Section section) {
        SectionResponseDtoWithoutRelations sectionResponseDtoWithoutRelations = new SectionResponseDtoWithoutRelations();
        sectionResponseDtoWithoutRelations.setId(section.getId());
        sectionResponseDtoWithoutRelations.setName(section.getName());
        return sectionResponseDtoWithoutRelations;
    }
}
