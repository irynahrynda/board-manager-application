package com.example.boardmanagerapp.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class SectionResponseDto {
    private Long id;
    private String name;
    private List<Long> tasksIds;
    private List<Long> boardsIds;
}
