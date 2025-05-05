package salad_leaf.spring_mvc_library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import salad_leaf.spring_mvc_library.model.Author;
import salad_leaf.spring_mvc_library.model.Book;
import salad_leaf.spring_mvc_library.repository.AuthorRepository;
import salad_leaf.spring_mvc_library.repository.BookRepository;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorService authorService;
    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public Book createBook(UUID id, Book book) {
        Author author = authorService.findAuthorById(id);
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Не нашёл такую книгу"));
    }
    public List<Book> authorBooks(UUID id) {
        Author author = authorService.findAuthorById(id);
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Page<Book> allBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book updateBook(Long id, Book book) {
        Book existingBook = findBookById(id);
        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());
        existingBook.setPublicationYear(book.getPublicationYear());
        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
}
