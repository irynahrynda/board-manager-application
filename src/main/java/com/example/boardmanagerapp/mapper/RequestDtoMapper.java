package com.example.boardmanagerapp.mapper;

public interface RequestDtoMapper<D, M> {
    M mapToModel(D dto);
}
