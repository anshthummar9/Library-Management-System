package com.library.management.repository;

import com.library.management.entity.Transaction;
import com.library.management.entity.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByMemberId(Long memberId);
    List<Transaction> findByBookId(Long bookId);
    List<Transaction> findByStatus(TransactionStatus status);
}