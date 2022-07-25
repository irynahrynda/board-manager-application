package com.example.boardmanagerapp.mapper;

import com.example.boardmanagerapp.dto.response.ColumnResponseDtoWithoutRelations;
import com.example.boardmanagerapp.model.Columnn;
import org.springframework.stereotype.Component;

@Component
public class ColumnMapperToDtoWithoutRelations implements MapperToDto<ColumnResponseDtoWithoutRelations, Columnn> {
    @Override
    public ColumnResponseDtoWithoutRelations mapToDto(Columnn columnn) {
        ColumnResponseDtoWithoutRelations columnResponseDtoWithoutRelations = new ColumnResponseDtoWithoutRelations();
        columnResponseDtoWithoutRelations.setId(columnn.getId());
        columnResponseDtoWithoutRelations.setName(columnn.getName());
        return columnResponseDtoWithoutRelations;
    }
}
