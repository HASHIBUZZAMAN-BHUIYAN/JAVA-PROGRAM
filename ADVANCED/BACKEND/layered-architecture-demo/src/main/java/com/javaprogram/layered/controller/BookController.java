package com.javaprogram.layered.controller;

import com.javaprogram.layered.dto.BookRequest;
import com.javaprogram.layered.entity.Book;
import com.javaprogram.layered.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    BookController(BookService service) { this.service = service; }

    @GetMapping                      public List<Book>             getAll()                          { return service.getAll(); }
    @GetMapping("/search")           public List<Book>             search(@RequestParam String q)    { return service.search(q); }
    @GetMapping("/author/{author}")  public List<Book>             byAuthor(@PathVariable String a)  { return service.byAuthor(a); }
    @GetMapping("/affordable")       public List<Book>             affordable(@RequestParam double max) { return service.affordable(max); }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok)
                                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody BookRequest req) { return service.create(req); }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody BookRequest req) {
        try { return ResponseEntity.ok(service.update(id, req)); }
        catch (NoSuchElementException e) { return ResponseEntity.notFound().build(); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try { service.delete(id); return ResponseEntity.noContent().build(); }
        catch (NoSuchElementException e) { return ResponseEntity.notFound().build(); }
    }
}
