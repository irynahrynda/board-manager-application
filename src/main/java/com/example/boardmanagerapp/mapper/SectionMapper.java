package com.example.boardmanagerapp.mapper;

import com.example.boardmanagerapp.dto.request.SectionRequestDto;
import com.example.boardmanagerapp.dto.response.SectionResponseDto;
import com.example.boardmanagerapp.model.Board;
import com.example.boardmanagerapp.model.Section;
import com.example.boardmanagerapp.model.Task;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class SectionMapper implements MapperToModel<SectionRequestDto, Section>,
        MapperToDto<SectionResponseDto, Section> {

    @Override
    public Section mapToModel(SectionRequestDto sectionRequestDto) {
        Section section = new Section();
        section.setName(sectionRequestDto.getName());
        return section;
    }

    @Override
    public SectionResponseDto mapToDto(Section section) {
        SectionResponseDto sectionResponseDto = new SectionResponseDto();
        sectionResponseDto.setId((section.getId()));
        sectionResponseDto.setName(section.getName());
        if (section.getBoards() != null && !section.getBoards().isEmpty()) {
            sectionResponseDto.setBoardsIds(section.getBoards().stream()
                    .map(Board::getId)
                    .collect(Collectors.toList()));
        }

        if(section.getTasks() != null) {
            sectionResponseDto.setTasksIds(section.getTasks()
                    .stream()
                    .map(Task::getId)
                    .collect(Collectors.toList()));
        }
        return sectionResponseDto;
    }
}
