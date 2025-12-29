package com.library.management.service;

import com.library.management.entity.Book;
import com.library.management.entity.Transaction;  // ADD THIS
import com.library.management.repository.BookRepository;
import com.library.management.repository.TransactionRepository;  // ADD THIS
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private TransactionRepository transactionRepository;  // ADD THIS
    
    public Book addBook(Book book) {
        try {
            if (book.getAvailableCopies() == null) {
                book.setAvailableCopies(book.getTotalCopies());
            }
            if (book.getTotalCopies() == null) {
                book.setTotalCopies(0);
                book.setAvailableCopies(0);
            }
            return bookRepository.save(book);
        } catch (Exception e) {
            throw new RuntimeException("Error adding book: " + e.getMessage(), e);
        }
    }
    
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }
    
    public Book updateBook(Long id, Book bookDetails) {
        Book book = getBookById(id);
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setPublisher(bookDetails.getPublisher());
        book.setPublishedDate(bookDetails.getPublishedDate());
        book.setCategory(bookDetails.getCategory());
        book.setTotalCopies(bookDetails.getTotalCopies());
        return bookRepository.save(book);
    }
    
    public void deleteBook(Long id) {
        Book book = getBookById(id);
        
        // Check if book has transactions
        List<Transaction> transactions = transactionRepository.findByBookId(id);
        if (!transactions.isEmpty()) {
            throw new RuntimeException("Cannot delete book. It has " + transactions.size() + " transaction(s) associated with it.");
        }
        
        bookRepository.delete(book);
    }
    
    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }
    
    public List<Book> searchByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }
}