package com.shubham.library_api.service;

import com.shubham.library_api.model.Book;
import com.shubham.library_api.model.Borrowing;
import com.shubham.library_api.model.Member;
import com.shubham.library_api.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private MemberService memberService;

    // Borrow a book
    public Borrowing borrowBook(Long bookId, Long memberId) {
        Book book = bookService.getBookById(bookId);
        Member member = memberService.getMemberById(memberId);

        // Check if book is available
        if( !book.isAvailable()) {
            throw new RuntimeException("Book is not available for borrowing");
        }

        // Create borrowing record
        Borrowing borrowing = new Borrowing(book, member);
        borrowing.setBorrowDate(LocalDate.now());

        //Marking book as unavailable
        book.setAvailable(false);
        bookService.updateBook(book.getId(),book);

        return borrowingRepository.save(borrowing);
    }


    // Return a book
    public Borrowing returnBook(Long borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new RuntimeException("Borrowing record not found"));

        // Check if already returned
        if(borrowing.isReturned()){
            throw new RuntimeException("Book already returned");
        }

        //Mark as returned
        borrowing.setReturned(true);
        borrowing.setReturnDate(LocalDate.now());

        // Mark book as available again
        Book book = borrowing.getBook();
        book.setAvailable(true);
        bookService.updateBook(book.getId(), book);

        return borrowingRepository.save(borrowing);
    }

    // Get all borrowings
    public List<Borrowing> getAllBorrowings() {
        return borrowingRepository.findAll();
    }

    // Get active borrowings( not returned)
    public List<Borrowing> getActiveBorrowings() {
        return borrowingRepository.findByReturnedFalse();
    }

    // Get Borrowing by members
    public List<Borrowing> getMemberBorrowing(Long memberId){
        Member member = memberService.getMemberById(memberId);
        return borrowingRepository.findByMember(member);
    }
}
