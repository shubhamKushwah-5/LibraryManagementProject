package com.shubham.library_api.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "borrowings")
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "borrow_date")
    private LocalDate borrowDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "is_returned")
    private boolean returned = false;

    //Constructor
    public Borrowing(){}

    public Borrowing(Book book, Member member) {
        this.book = book;
        this.member = member;
        this.borrowDate = LocalDate.now();
        this.returned = false;
    }

    //Getters and setters
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public Book getBook(){return book;}
    public void setBook(Book book){this.book = book;}

    public Member getMember() {return member;}
    public void setMember(Member member ){this.member = member;}

    public LocalDate getBorrowDate(){return borrowDate;}
    public void setBorrowDate(LocalDate borrowDate){this.borrowDate = borrowDate;}

    public LocalDate getReturnDate(){return returnDate;}
    public void setReturnDate(LocalDate returnDate) {this.returnDate = returnDate;}

    public boolean isReturned(){return returned;}
    public void setReturned(boolean returned){this.returned = returned;}
}
