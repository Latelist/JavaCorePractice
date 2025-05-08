package salad_leaf.jdbc_practice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import salad_leaf.jdbc_practice.entities.Book;
import salad_leaf.jdbc_practice.service.BookService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable UUID id) {
        return bookService.findById(id);
    }

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable UUID id) {
        bookService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable UUID id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }
}
