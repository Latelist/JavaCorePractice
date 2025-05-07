package salad_leaf.jdbc_practice.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import salad_leaf.jdbc_practice.entities.Book;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository{
    Book save(Book book);
    void deleteById(UUID id);
    List<Book> findAll();
    Book findById(UUID id);

}
