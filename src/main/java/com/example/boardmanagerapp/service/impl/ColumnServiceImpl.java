package com.example.boardmanagerapp.service.impl;

import com.example.boardmanagerapp.model.Column;
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
    public Column createColumn(Column column) {
        return columnRepository.save(column);
    }

    @Override
    public Column getColumnById(Long id) {
        return columnRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get column by id " + id));
    }

    @Override
    public List<Column> getAllColumns() {
        return columnRepository.findAll();
    }

    @Override
    public void deleteColumnsById(Long id) {
        columnRepository.findById(id);
    }
}
