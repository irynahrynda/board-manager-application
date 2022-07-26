package com.example.boardmanagerapp.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class BoardResponseDtoWithSections {
    private Long id;
    private String name;
    private String backgroundImagePath;
    private List<SectionResponseDtoWithTasks> sectionResponseDtoWithTasks;
}
