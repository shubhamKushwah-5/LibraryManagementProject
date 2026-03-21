package com.shubham.library_api.controller;

import com.shubham.library_api.model.Borrowing;
import com.shubham.library_api.repository.BorrowingRepository;
import com.shubham.library_api.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowings")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    //Borrow a book
    @PostMapping("/borrow")
    public ResponseEntity<Borrowing> borrowBook(
            @RequestParam Long bookId,
            @RequestParam Long memberId) {

        try{
            Borrowing borrowing = borrowingService.borrowBook(bookId,memberId);
            return new ResponseEntity<>(borrowing, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Return a Book
    @PostMapping("/return/{borrowingId}")
    public ResponseEntity<Borrowing> returnBook(@PathVariable Long borrowingId) {
        try{
            Borrowing borrowing = borrowingService.returnBook(borrowingId);
            return ResponseEntity.ok(borrowing);
        } catch (RuntimeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Get all borrowings
    @GetMapping
    public List<Borrowing> getAllBorrowings() {
        return borrowingService.getAllBorrowings();
    }

    // Get active borrowings
    @GetMapping("/active")
    public List<Borrowing> getActiveBorrowings(){
        return borrowingService.getActiveBorrowings();
    }

    //Get member's borrowings
    @GetMapping("/member/{memberId}")
    public List<Borrowing> getMemberBorrowings(@PathVariable Long memberId) {
        return borrowingService.getMemberBorrowing(memberId);
    }

}
