package salad_leaf.spring_mvc_library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import salad_leaf.spring_mvc_library.model.Author;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
    Page<Author> findAll(Pageable pageable);
}
