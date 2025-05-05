package salad_leaf.spring_mvc_library.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Author {
    @GeneratedValue
    @Id
    @JsonView(Views.AuthorSummary.class)
    private UUID id;
    @JsonView(Views.AuthorSummary.class)
    private String name;
    @JsonView(Views.AuthorDetails.class)
    private int birthYear;
    @JsonView(Views.AuthorDetails.class)
    private int deathYear;
    @JsonView(Views.AuthorDetails.class)
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Book> books;

    public Author() {}

}
