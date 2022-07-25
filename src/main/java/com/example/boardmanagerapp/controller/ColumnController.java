package com.example.boardmanagerapp.controller;

import com.example.boardmanagerapp.dto.request.ColumnRequestDto;
import com.example.boardmanagerapp.dto.response.ColumnResponseDto;
import com.example.boardmanagerapp.dto.response.ColumnResponseDtoWithoutRelations;
import com.example.boardmanagerapp.mapper.MapperToDto;
import com.example.boardmanagerapp.mapper.MapperToModel;
import com.example.boardmanagerapp.model.Columnn;
import com.example.boardmanagerapp.service.ColumnService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/columns")
public class ColumnController {
    private final ColumnService columnService;
    private final MapperToModel<ColumnRequestDto, Columnn> mapperToModel;
    private final MapperToDto<ColumnResponseDto, Columnn> mapperToDto;

    private final MapperToDto<ColumnResponseDtoWithoutRelations, Columnn> mapperToDtoWithoutRelations;

    public ColumnController(ColumnService columnService, MapperToModel<ColumnRequestDto, Columnn> mapperToModel, MapperToDto<ColumnResponseDto, Columnn> mapperToDto, MapperToDto<ColumnResponseDtoWithoutRelations, Columnn> mapperToDtoWithoutRelations) {
        this.columnService = columnService;
        this.mapperToModel = mapperToModel;
        this.mapperToDto = mapperToDto;
        this.mapperToDtoWithoutRelations = mapperToDtoWithoutRelations;
    }


    @PostMapping
    public ColumnResponseDto createColumn(@RequestBody ColumnRequestDto columnRequestDto) {
        return mapperToDto.mapToDto(columnService.createColumn(
                mapperToModel.mapToModel(columnRequestDto)));
    }

    @GetMapping("/{id}")
    public ColumnResponseDto getColumnById(@PathVariable Long id) {
        return mapperToDto.mapToDto(columnService.getColumnById(id));
    }

    @GetMapping
    public List<ColumnResponseDto> getAllColumns() {
        return columnService.getAllColumns()
                .stream()
                .map(mapperToDto::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ColumnResponseDto updateColumn(@PathVariable Long id,
                                          @RequestBody ColumnRequestDto columnRequestDto) {
        Columnn columnn = columnService.getColumnById(id);
        columnn.setName(columnRequestDto.getName());
        columnService.createColumn(columnn);
        return mapperToDto.mapToDto(columnn);
    }

    @DeleteMapping("/{id}")
    public ColumnResponseDtoWithoutRelations deleteColumnById(@PathVariable Long id) {
        Columnn columnn = columnService.getColumnById(id);
        columnService.deleteColumnById(id);
        return mapperToDtoWithoutRelations.mapToDto(columnn);
    }

    @GetMapping("/by-name")
    public ColumnResponseDto getColumnByName (@RequestParam String name) {
        return mapperToDto.mapToDto(columnService.getColumnByName(name));
    }
}
