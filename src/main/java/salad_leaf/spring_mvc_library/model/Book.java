package salad_leaf.spring_mvc_library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book {
    @GeneratedValue
    @Id
    @JsonView(Views.BookSummary.class)
    private Long id;
    @JsonView(Views.BookSummary.class)
    private String title;
    @JsonView(Views.BookDetails.class)
    private int publicationYear;

    @ManyToOne
    @JsonView(Views.BookDetails.class)
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private Author author;

    public Book(){}

}
