package com.library.management.controller;

import com.library.management.entity.Transaction;
import com.library.management.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;
    
    @PostMapping("/issue")
    public ResponseEntity<Transaction> issueBook(@RequestParam Long bookId, @RequestParam Long memberId) {
        return new ResponseEntity<>(transactionService.issueBook(bookId, memberId), HttpStatus.CREATED);
    }
    
    @PutMapping("/return/{id}")
    public ResponseEntity<Transaction> returnBook(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.returnBook(id));
    }
    
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }
    
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<Transaction>> getMemberTransactions(@PathVariable Long memberId) {
        return ResponseEntity.ok(transactionService.getMemberTransactions(memberId));
    }
    
    @GetMapping("/overdue")
    public ResponseEntity<List<Transaction>> getOverdueTransactions() {
        return ResponseEntity.ok(transactionService.getOverdueTransactions());
    }
}