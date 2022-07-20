package com.example.boardmanagerapp.service;

import com.example.boardmanagerapp.model.Board;

import java.util.List;

public interface BoardService {
    Board createBoard(Board board);

    Board getBoardById(Long id);

    List<Board> getAllBoards();

    void deleteBoardById(Long id);
}
