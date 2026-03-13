package service;

import entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BookRepository;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book save(Book newBook) {
        return bookRepository.save(newBook);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findByIsbn(int isbn) {
        return bookRepository.findById(isbn).orElse(null);
    }

    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public void deleteByIsbn(int isbn) {
        try {
            bookRepository.deleteById(isbn);
        } catch (Exception e) {
            System.err.println("Unable to delete Book with ID: " + isbn);
        }
    }
}
