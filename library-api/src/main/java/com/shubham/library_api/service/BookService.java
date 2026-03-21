package com.shubham.library_api.service;

import com.shubham.library_api.model.Book;
import com.shubham.library_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Get all books
    public List<Book> getAllBooks(){
         return  bookRepository.findAll();
    }

    // Get book by ID
    public Book getBookById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    // Add book
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // Update Book
    public Book updateBook(Long id, Book bookDetails){
        Book old = getBookById(id);

        old.setTitle(bookDetails.getTitle());
        old.setAuthor(bookDetails.getAuthor());
        old.setIsbn(bookDetails.getIsbn());

        return bookRepository.save(old);
    }

    // Delete book
    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

    //Get Available Books
    public List<Book> getAvailableBooks(){
        return bookRepository.findByAvailableTrue();
    }

    // Get book By Author
    public List<Book> getBookByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

}
