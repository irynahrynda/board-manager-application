package com.example.boardmanagerapp.service;

import com.example.boardmanagerapp.dto.request.SectionRequestDto;
import com.example.boardmanagerapp.dto.request.TaskRequestDto;
import com.example.boardmanagerapp.dto.response.TaskResponseDto;

import java.util.List;

public interface TaskService {
    TaskResponseDto createTask(Long id, TaskRequestDto taskRequestDto);

    TaskResponseDto getTaskById(Long id);

    List<TaskResponseDto> getAllTasks();

    TaskResponseDto deleteTasksById(Long id);

    TaskResponseDto updateTaskById(Long id, TaskRequestDto taskRequestDto);

    TaskResponseDto updateStatus(Long id, SectionRequestDto sectionRequestDto);
}
