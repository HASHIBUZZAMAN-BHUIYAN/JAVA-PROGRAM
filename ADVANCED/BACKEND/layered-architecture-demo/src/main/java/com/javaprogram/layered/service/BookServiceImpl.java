package com.javaprogram.layered.service;

import com.javaprogram.layered.dto.BookRequest;
import com.javaprogram.layered.entity.Book;
import com.javaprogram.layered.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repo;

    BookServiceImpl(BookRepository repo) { this.repo = repo; }

    @Override public List<Book>     getAll()                  { return repo.findAll(); }
    @Override public Optional<Book> getById(Long id)          { return repo.findById(id); }
    @Override public List<Book>     byAuthor(String author)   { return repo.findByAuthorIgnoreCase(author); }
    @Override public List<Book>     search(String keyword)    { return repo.findByTitleContainingIgnoreCase(keyword); }
    @Override public List<Book>     affordable(double max)    { return repo.findByPriceLessThanEqual(max); }

    @Override
    public Book create(BookRequest req) {
        return repo.save(new Book(req.title(), req.author(), req.isbn(), req.price(), req.year()));
    }

    @Override
    public Book update(Long id, BookRequest req) {
        Book book = repo.findById(id).orElseThrow(() -> new NoSuchElementException("Book not found: " + id));
        book.setTitle(req.title()); book.setAuthor(req.author());
        book.setIsbn(req.isbn());   book.setPrice(req.price()); book.setYear(req.year());
        return repo.save(book);
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NoSuchElementException("Book not found: " + id);
        repo.deleteById(id);
    }
}
