package com.example.boardmanagerapp.dto.response;

import lombok.Data;

@Data
public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private Long columnId;
}
