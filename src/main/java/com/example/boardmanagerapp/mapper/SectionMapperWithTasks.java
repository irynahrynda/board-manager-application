package com.example.boardmanagerapp.mapper;

import com.example.boardmanagerapp.dto.response.SectionResponseDtoWithTasks;
import com.example.boardmanagerapp.dto.response.TaskResponseDto;
import com.example.boardmanagerapp.model.Board;
import com.example.boardmanagerapp.model.Section;
import com.example.boardmanagerapp.model.Task;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SectionMapperWithTasks implements MapperToDto<SectionResponseDtoWithTasks, Section> {
    private final MapperToDto<TaskResponseDto, Task> taskMapperToDto;

    public SectionMapperWithTasks(MapperToDto<TaskResponseDto, Task> taskMapperToDto) {
        this.taskMapperToDto = taskMapperToDto;
    }


    @Override
    public SectionResponseDtoWithTasks mapToDto(Section section) {
        SectionResponseDtoWithTasks sectionResponseDtoWithTasks = new SectionResponseDtoWithTasks();
        sectionResponseDtoWithTasks.setId(section.getId());
        sectionResponseDtoWithTasks.setName(section.getName());

        if (section.getBoards() != null && !section.getBoards().isEmpty()) {
            sectionResponseDtoWithTasks.setBoardIds(section.getBoards().stream()
                    .map(Board::getId)
                    .collect(Collectors.toList()));
        }

        if (section.getTasks() != null) {
            sectionResponseDtoWithTasks.setTasks(section.getTasks()
                    .stream()
                    .map(taskMapperToDto::mapToDto)
                    .collect(Collectors.toList()));
        }

        return sectionResponseDtoWithTasks;
    }
}
