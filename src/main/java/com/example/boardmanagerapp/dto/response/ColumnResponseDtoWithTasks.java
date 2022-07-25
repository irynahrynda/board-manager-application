package com.example.boardmanagerapp.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ColumnResponseDtoWithTasks {
    private Long id;
    private String name;
    private List<TaskResponseDto> tasks;
    private List<Long> boardIds;
}
