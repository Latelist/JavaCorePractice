package salad_leaf.jdbc_practice.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import salad_leaf.jdbc_practice.entities.Book;
import salad_leaf.jdbc_practice.repository.BookRepository;
import salad_leaf.jdbc_practice.repository.BookRepositoryImpl;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    private BookRepositoryImpl bookRepositoryImpl;

    public BookService(BookRepositoryImpl bookRepositoryImpl) {
        this.bookRepositoryImpl = bookRepositoryImpl;
    }

    public Book createBook(Book book) {
        return bookRepositoryImpl.save(book);
    }

    public Book findById(UUID id){
        return bookRepositoryImpl.findById(id);
    }

    public List<Book> findAll() {
        return bookRepositoryImpl.findAll();
    }

    public void deleteById(UUID id){
        bookRepositoryImpl.deleteById(id);
    }

    public Book updateBook(UUID id, Book book) {
        Book existingBook = bookRepositoryImpl.findById(id);
        existingBook.setPublicationYear(book.getPublicationYear());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());
        return bookRepositoryImpl.save(existingBook);
    }


}
