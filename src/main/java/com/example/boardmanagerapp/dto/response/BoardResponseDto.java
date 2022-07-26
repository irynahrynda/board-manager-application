package com.example.boardmanagerapp.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class BoardResponseDto {
    private Long id;
    private String name;
    private String backgroundImagePath;
    private List<Long> sectionIds;
}
