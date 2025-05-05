package salad_leaf.spring_mvc_library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import salad_leaf.spring_mvc_library.model.Book;
import salad_leaf.spring_mvc_library.model.Views;
import salad_leaf.spring_mvc_library.service.BookService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/author/{id}")
    public MappingJacksonValue createBook(@PathVariable UUID id, @RequestBody Book book) {
        Book saved = bookService.createBook(id, book);
        MappingJacksonValue value = new MappingJacksonValue(saved);
        value.setSerializationView(Views.BookDetails.class);
        return value;
    }

    @GetMapping("/{id}")
    public MappingJacksonValue findBookById(@PathVariable Long id) {
        Book book = bookService.findBookById(id);
        MappingJacksonValue value = new MappingJacksonValue(book);
        value.setSerializationView(Views.BookDetails.class);
        return value;
    }

    @GetMapping
    public MappingJacksonValue getAllBooks(Pageable pageable) {
        Page<Book> page = bookService.allBooks(pageable);
        MappingJacksonValue value = new MappingJacksonValue(page);
        value.setSerializationView(Views.BookSummary.class);
        return value;
    }

    @GetMapping("/author/{id}")
    public MappingJacksonValue findBooksByAuthor(@PathVariable UUID id) {
        List<Book> books = bookService.authorBooks(id);
        MappingJacksonValue value = new MappingJacksonValue(books);
        value.setSerializationView(Views.BookSummary.class);
        return value;
    }

    @PutMapping("/{id}")
    public MappingJacksonValue updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updated = bookService.updateBook(id, book);
        MappingJacksonValue value = new MappingJacksonValue(updated);
        value.setSerializationView(Views.BookDetails.class);
        return value;
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
