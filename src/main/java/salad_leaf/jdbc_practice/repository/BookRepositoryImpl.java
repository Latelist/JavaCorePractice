package salad_leaf.jdbc_practice.repository;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import salad_leaf.jdbc_practice.entities.Book;

import java.util.List;
import java.util.UUID;

@Component
public class BookRepositoryImpl implements BookRepository {
    private final JdbcTemplate jdbcTemplate;

    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(UUID.randomUUID());
        }
        // Если книга уже есть — обновляем, иначе вставляем
        String checkSql = "SELECT COUNT(*) FROM books WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, book.getId());

        if (count > 0) {
            String updateSql = "UPDATE books SET title = ?, author = ?, publication_Year = ? WHERE id = ?";
            jdbcTemplate.update(updateSql,
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPublicationYear(),
                    book.getId());
        } else {
            String insertSql = "INSERT INTO books(id, title, author, publication_Year) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(insertSql,
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPublicationYear());
        }
        return book;
    }


    public void deleteById(UUID id) {
        String sql = "DELETE FROM books WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Book> findAll() {
        String sql = "SELECT * FROM books";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public Book findById(UUID id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        List<Book> books = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), id);
        return books.stream().findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Нет книги с таким id"));
    }
}
