package com.javaprogram.layered.repository;

import com.javaprogram.layered.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorIgnoreCase(String author);
    List<Book> findByTitleContainingIgnoreCase(String keyword);
    List<Book> findByPriceLessThanEqual(double maxPrice);
}
