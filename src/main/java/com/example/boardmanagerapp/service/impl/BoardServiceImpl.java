package com.example.boardmanagerapp.service.impl;

import com.example.boardmanagerapp.model.Board;
import com.example.boardmanagerapp.repository.BoardRepository;
import com.example.boardmanagerapp.service.BoardService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public Board getBoardById(Long id) {
        return boardRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't get board by id " + id));
    }

    @Override
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @Override
    public Board deleteBoardById(Long id) {
        Board boardToDelete = boardRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't delete board by id " + id));
        boardRepository.deleteById(id);
        return boardToDelete;
    }

    @Override
    public Board getBoardByName(String name) {
        return boardRepository.getBoardByName(name);
    }
}
