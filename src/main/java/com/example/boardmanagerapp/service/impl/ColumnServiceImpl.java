package com.example.boardmanagerapp.service.impl;

import com.example.boardmanagerapp.model.Columnn;
import com.example.boardmanagerapp.repository.ColumnRepository;
import com.example.boardmanagerapp.service.ColumnService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnServiceImpl implements ColumnService {
    private final ColumnRepository columnRepository;

    public ColumnServiceImpl(ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
    }

    @Override
    public Columnn createColumn(Columnn columnn) {
        return columnRepository.save(columnn);
    }

    @Override
    public Columnn getColumnById(Long id) {
        return columnRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get column by id " + id));
    }

    @Override
    public List<Columnn> getAllColumns() {
        return columnRepository.findAll();
    }

    public Columnn deleteColumnById(Long id) {
        Columnn columnnToDelete = columnRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't delete column by id " + id));
        columnRepository.deleteById(id);
        return columnnToDelete;
    }

    @Override
    public Columnn getColumnByName(String name) {
      return columnRepository.getColumnnByName(name);
    }
}
