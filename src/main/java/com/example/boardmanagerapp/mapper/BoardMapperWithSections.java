package com.example.boardmanagerapp.mapper;

import com.example.boardmanagerapp.dto.response.BoardResponseDtoWithSections;
import com.example.boardmanagerapp.dto.response.SectionResponseDtoWithTasks;
import com.example.boardmanagerapp.model.Board;
import com.example.boardmanagerapp.model.Section;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BoardMapperWithSections implements MapperToDto<BoardResponseDtoWithSections, Board> {

    private final MapperToDto<SectionResponseDtoWithTasks, Section> sectionResponseMapperDtoWithTasks;

    public BoardMapperWithSections(MapperToDto<SectionResponseDtoWithTasks, Section> sectionResponseMapperDtoWithTasks) {
        this.sectionResponseMapperDtoWithTasks = sectionResponseMapperDtoWithTasks;
    }

    @Override
    public BoardResponseDtoWithSections mapToDto(Board board) {
        BoardResponseDtoWithSections boardResponseDtoWithSections = new BoardResponseDtoWithSections();
        boardResponseDtoWithSections.setId(board.getId());
        boardResponseDtoWithSections.setName(board.getName());
        boardResponseDtoWithSections.setBackgroundImagePath(board.getBackgroundImagePath());
        if (board.getSections() != null) {
            boardResponseDtoWithSections.setSectionResponseDtoWithTasks(board.getSections().stream()
                    .map(sectionResponseMapperDtoWithTasks::mapToDto)
                    .collect(Collectors.toList()));
        }
        return boardResponseDtoWithSections;
    }
}
