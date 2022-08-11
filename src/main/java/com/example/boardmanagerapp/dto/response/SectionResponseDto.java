package com.example.boardmanagerapp.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class SectionResponseDto {
    private Long id;
    private String name;
    private List<Long> tasksIds;
    private List<Long> boardsIds;
}
