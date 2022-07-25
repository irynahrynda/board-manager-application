package com.example.boardmanagerapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "boards")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String backgroundImagePath;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "board")
    private List<Columnn> columnns;

    public Board() {
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

    public String getBackgroundImagePath() {
        return backgroundImagePath;
    }

    public void setBackgroundImagePath(String backgroundImagePath) {
        this.backgroundImagePath = backgroundImagePath;
    }

    public List<Columnn> getColumns() {
        return columnns;
    }

    public void setColumns(List<Columnn> columnns) {
        this.columnns = columnns;
    }
}
