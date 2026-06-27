package com.javaprogram.layered.service;

import com.javaprogram.layered.dto.BookRequest;
import com.javaprogram.layered.entity.Book;
import java.util.List;
import java.util.Optional;

/**
 * Service interface — defines what operations are available without revealing how.
 * The controller depends on this interface, not on the implementation.
 */
public interface BookService {
    List<Book>      getAll();
    Optional<Book>  getById(Long id);
    Book            create(BookRequest req);
    Book            update(Long id, BookRequest req);
    void            delete(Long id);
    List<Book>      search(String keyword);
    List<Book>      byAuthor(String author);
    List<Book>      affordable(double maxPrice);
}
