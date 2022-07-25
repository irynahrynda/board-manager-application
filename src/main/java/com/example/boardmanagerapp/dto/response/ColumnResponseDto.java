package com.example.boardmanagerapp.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class ColumnResponseDto {
    private Long id;
    private String name;
    private List<Long> tasksIds;
    private List<Long> boardsIds;
}
