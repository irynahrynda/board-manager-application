package com.example.boardmanagerapp.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ColumnRequestDto {
    private String name;
    private List<Long> tasksIds;
    private Long boardId;
}
