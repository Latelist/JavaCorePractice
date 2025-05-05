package salad_leaf.spring_mvc_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import salad_leaf.spring_mvc_library.model.Author;
import salad_leaf.spring_mvc_library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(Author author);
    List<Book> findByTitle(String title);
    @NonNull
    Page<Book> findAll(@NonNull Pageable pageable);
}
