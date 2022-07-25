package com.example.boardmanagerapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "columns")
public class Columnn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "columnn")
    private List<Task> tasks;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public Columnn() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}