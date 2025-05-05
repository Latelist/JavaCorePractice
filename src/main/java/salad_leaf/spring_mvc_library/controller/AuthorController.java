package salad_leaf.spring_mvc_library.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import salad_leaf.spring_mvc_library.model.Author;
import salad_leaf.spring_mvc_library.model.Views;
import salad_leaf.spring_mvc_library.service.AuthorService;

import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public MappingJacksonValue createAuthor(@RequestBody Author author) {
        Author saved = authorService.createAuthor(author);
        MappingJacksonValue value = new MappingJacksonValue(saved);
        value.setSerializationView(Views.AuthorDetails.class);
        return value;
    }

    @GetMapping
    public Page<Author> findAllAuthors(Pageable pageable) {
        return authorService.findAllAuthors(pageable);
    }

    @GetMapping("/{id}")
    public MappingJacksonValue findAuthor(@PathVariable UUID id) {
        Author author = authorService.findAuthorById(id);
        MappingJacksonValue value = new MappingJacksonValue(author);
        value.setSerializationView(Views.AuthorDetails.class);
        return value;
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable UUID id) {
        authorService.deleteAuthor(id);
    }
}
