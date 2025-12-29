package com.library.management.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Book book;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "transactions"})
    private Member member;
    
    @Column(nullable = false)
    private LocalDate issueDate;
    
    private LocalDate dueDate;
    private LocalDate returnDate;
    
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    
    private Double fine;
    
    public Transaction() {}
    
    public Transaction(Book book, Member member) {
        this.book = book;
        this.member = member;
        this.issueDate = LocalDate.now();
        this.dueDate = LocalDate.now().plusDays(14);
        this.status = TransactionStatus.ISSUED;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    
    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }
    
    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }
    
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    
    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    
    public TransactionStatus getStatus() { return status; }
    public void setStatus(TransactionStatus status) { this.status = status; }
    
    public Double getFine() { return fine; }
    public void setFine(Double fine) { this.fine = fine; }
}