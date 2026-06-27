package com.javaprogram.layered.dto;

// Java 16+ record — concise DTO with auto-generated constructor, getters, equals, hashCode
public record BookRequest(String title, String author, String isbn, double price, int year) {}
