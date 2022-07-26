package com.example.boardmanagerapp.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class SectionResponseDtoWithTasks {
    private Long id;
    private String name;
    private List<TaskResponseDto> tasks;
    private List<Long> boardIds;
}
