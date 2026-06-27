package com.javaprogram.layered.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String title;
    @Column(nullable = false) private String author;
    private String isbn;
    private double price;
    private int    year;

    public Book() {}
    public Book(String title, String author, String isbn, double price, int year) {
        this.title = title; this.author = author;
        this.isbn = isbn;   this.price = price; this.year = year;
    }

    public Long   getId()     { return id; }
    public String getTitle()  { return title; }
    public String getAuthor() { return author; }
    public String getIsbn()   { return isbn; }
    public double getPrice()  { return price; }
    public int    getYear()   { return year; }

    public void setTitle(String t)   { this.title  = t; }
    public void setAuthor(String a)  { this.author = a; }
    public void setIsbn(String i)    { this.isbn   = i; }
    public void setPrice(double p)   { this.price  = p; }
    public void setYear(int y)       { this.year   = y; }
}
