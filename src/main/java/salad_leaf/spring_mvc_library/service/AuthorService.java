package salad_leaf.spring_mvc_library.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import salad_leaf.spring_mvc_library.model.Author;
import salad_leaf.spring_mvc_library.repository.AuthorRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Page<Author> findAllAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    public Author findAuthorById(UUID id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Не знаю такого автора"));
    }

    public void deleteAuthor(UUID id) {
        Author author = findAuthorById(id);
        authorRepository.delete(author);
    }
}
