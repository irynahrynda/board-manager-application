package com.example.boardmanagerapp.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class BoardResponseDtoWithColumns {
    private Long id;
    private String name;
    private String backgroundImagePath;
    private List<ColumnResponseDtoWithTasks> columnsWithTasks;
}
