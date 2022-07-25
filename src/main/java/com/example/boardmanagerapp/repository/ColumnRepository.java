package com.example.boardmanagerapp.repository;

import com.example.boardmanagerapp.model.Columnn;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnRepository extends JpaRepository<Columnn, Long> {
    Columnn getColumnnByName(String name);
}
