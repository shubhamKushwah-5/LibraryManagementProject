package com.shubham.library_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 1, max = 200)
    private String title;

    @NotBlank(message = "Author cannot be empty")
    private String author;

    @Column(unique = true)
    @NotBlank(message = "ISBN cannot be empty")
    private String isbn;

    @Column(name = "is_available")
    private boolean available = true;

    //Getter and setters

    public Long getId() {return id;}
    public void setId(Long id){this.id = id;}

    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}

    public String getAuthor() { return author;}
    public void setAuthor(String author){this.author = author;}

    public String getIsbn(){return isbn;}
    public void setIsbn(String isbn){this.isbn = isbn;}

    public boolean isAvailable(){return available;}
    public void setAvailable(boolean available){this.available = available;}

    //Constructor
    public Book(){}

    public Book( String title, String author, String isbn){

        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
    }
}
