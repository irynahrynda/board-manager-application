package com.example.boardmanagerapp.mapper;

public interface MapperToModel<D, M> {
    M mapToModel(D dto);
}
