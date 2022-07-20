package com.example.boardmanagerapp.service;

import com.example.boardmanagerapp.model.Column;
import java.util.List;

public interface ColumnService {
    Column createColumn(Column column);

    Column getColumnById(Long id);

    List<Column> getAllColumns();

    void deleteColumnsById(Long id);
}
