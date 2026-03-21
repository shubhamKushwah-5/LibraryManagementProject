package com.shubham.library_api.repository;

import com.shubham.library_api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository <Book, Long>{

    //find by ISBN
    Optional<Book> findByIsbn(String isbn);

    //find by author
    List<Book> findByAuthor(String author);

    //find available books
    List<Book> findByAvailableTrue();
}
