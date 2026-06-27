package com.javaprogram.crud.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private boolean completed = false;

    // JPA requires no-arg constructor
    public Task() {}

    public Task(String title, String description) {
        this.title       = title;
        this.description = description;
    }

    public Long    getId()          { return id; }
    public String  getTitle()       { return title; }
    public String  getDescription() { return description; }
    public boolean isCompleted()    { return completed; }

    public void setTitle(String title)             { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setCompleted(boolean completed)    { this.completed = completed; }
}
