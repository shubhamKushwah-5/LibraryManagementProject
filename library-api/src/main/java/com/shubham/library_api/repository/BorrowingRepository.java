package com.shubham.library_api.repository;

import com.shubham.library_api.model.Book;
import com.shubham.library_api.model.Borrowing;
import com.shubham.library_api.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowingRepository extends JpaRepository<Borrowing,Long> {

    //find all borrowing by member
    List<Borrowing> findByMember(Member member);

    // find all active borrowing( not returned)
    List<Borrowing> findByReturnedFalse();

    // find avtive borrowing for specific book
    Optional<Borrowing> findByBookAndReturnedFalse(Book book);
}
