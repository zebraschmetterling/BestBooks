package service;

import entity.Book;
import entity.User;
import entity.read;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.readRepository;

import java.util.List;

@Service
public class readService {

    @Autowired
    private readRepository readRepository;

    public read save(read newRead) {
        return readRepository.save(newRead);
    }

    public List<read> findAll() {
        return readRepository.findAll();
    }

    public read findById(int readId) {
        return readRepository.findById(readId).orElse(null);
    }

    public List<read> findByUser(User user) {
        return readRepository.findByUser(user);
    }

    public List<read> findByBook(Book book) {
        return readRepository.findByBook(book);
    }

    public List<read> findCompletedBooks() {
        return readRepository.findCompletedBooks();
    }

    public List<read> findUnfinishedBooks() {
        return readRepository.findUnfinishedBooks();
    }

    public void deleteById(int readId) {
        try {
            readRepository.deleteById(readId);
        } catch (Exception e) {
            System.err.println("Unable to delete read with ID: " + readId);
        }
    }

}
