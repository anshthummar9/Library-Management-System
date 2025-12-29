package com.library.management.service;

import com.library.management.entity.*;
import com.library.management.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
public class TransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private MemberService memberService;
    
    public Transaction issueBook(Long bookId, Long memberId) {
        try {
            Book book = bookService.getBookById(bookId);
            Member member = memberService.getMemberById(memberId);
            
            if (book.getAvailableCopies() == null || book.getAvailableCopies() <= 0) {
                throw new RuntimeException("No copies available for this book");
            }
            
            // Decrease available copies
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            
            // Create transaction
            Transaction transaction = new Transaction();
            transaction.setBook(book);
            transaction.setMember(member);
            transaction.setIssueDate(LocalDate.now());
            transaction.setDueDate(LocalDate.now().plusDays(14));
            transaction.setStatus(TransactionStatus.ISSUED);
            transaction.setFine(0.0);
            
            return transactionRepository.save(transaction);
        } catch (Exception e) {
            throw new RuntimeException("Error issuing book: " + e.getMessage(), e);
        }
    }
    
    public Transaction returnBook(Long transactionId) {
        try {
            Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
            
            Book book = transaction.getBook();
            
            // Increase available copies
            if (book.getAvailableCopies() == null) {
                book.setAvailableCopies(1);
            } else {
                book.setAvailableCopies(book.getAvailableCopies() + 1);
            }
            
            transaction.setReturnDate(LocalDate.now());
            transaction.setStatus(TransactionStatus.RETURNED);
            
            // Calculate fine if overdue
            if (transaction.getDueDate() != null && LocalDate.now().isAfter(transaction.getDueDate())) {
                long daysOverdue = ChronoUnit.DAYS.between(transaction.getDueDate(), LocalDate.now());
                transaction.setFine(daysOverdue * 2.0);
            } else {
                transaction.setFine(0.0);
            }
            
            return transactionRepository.save(transaction);
        } catch (Exception e) {
            throw new RuntimeException("Error returning book: " + e.getMessage(), e);
        }
    }
    
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    
    public List<Transaction> getMemberTransactions(Long memberId) {
        return transactionRepository.findByMemberId(memberId);
    }
    
    public List<Transaction> getOverdueTransactions() {
        return transactionRepository.findByStatus(TransactionStatus.OVERDUE);
    }
}