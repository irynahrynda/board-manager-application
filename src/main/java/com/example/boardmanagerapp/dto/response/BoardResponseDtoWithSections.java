package com.example.boardmanagerapp.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class BoardResponseDtoWithSections {
    private Long id;
    private String name;
    private String backgroundImagePath;
    private List<SectionResponseDtoWithTasks> sectionResponseDtoWithTasks;
}
