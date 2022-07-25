package com.example.boardmanagerapp.mapper;

public interface MapperToDto<D, M> {
    D mapToDto(M model);
}
