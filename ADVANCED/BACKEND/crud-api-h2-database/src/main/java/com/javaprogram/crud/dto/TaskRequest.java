package com.javaprogram.crud.dto;

public class TaskRequest {
    private String  title;
    private String  description;
    private boolean completed;

    public String  getTitle()       { return title; }
    public String  getDescription() { return description; }
    public boolean isCompleted()    { return completed; }
    public void setTitle(String t)          { this.title = t; }
    public void setDescription(String d)    { this.description = d; }
    public void setCompleted(boolean c)     { this.completed = c; }
}
