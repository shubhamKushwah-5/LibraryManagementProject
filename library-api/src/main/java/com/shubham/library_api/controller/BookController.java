package com.shubham.library_api.controller;

import com.shubham.library_api.model.Book;
import com.shubham.library_api.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Get all books
    @GetMapping
    public List<Book> getAllBook(){
        return bookService.getAllBooks();
    }

    // Get book by ID
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    // Add book
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book){
        Book saved = bookService.addBook(book);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Update book
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @Valid @RequestBody Book book) {
        return bookService.updateBook(id,book);
    }

    // Delete book
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully");
    }

    // Get Available books
    @GetMapping("/available")
    public List<Book> getAvailableBook() {
        return bookService.getAvailableBooks();
    }

    // Get books by author
    @GetMapping("/author/{author}")
    public List<Book> getBookByAuthor(@PathVariable String author) {
        return bookService.getBookByAuthor(author);
    }
}
