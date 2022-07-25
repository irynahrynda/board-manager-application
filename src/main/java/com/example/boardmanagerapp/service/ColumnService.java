package com.example.boardmanagerapp.service;

import com.example.boardmanagerapp.model.Columnn;

import java.util.List;

public interface ColumnService {
    Columnn createColumn(Columnn columnn);

    Columnn getColumnById(Long id);

    List<Columnn> getAllColumns();

    Columnn deleteColumnById(Long id);

    Columnn getColumnByName(String name);
}
