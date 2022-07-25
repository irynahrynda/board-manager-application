package com.example.boardmanagerapp.repository;

import com.example.boardmanagerapp.model.Board;
import com.example.boardmanagerapp.model.Columnn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board getBoardByName(String name);
}
