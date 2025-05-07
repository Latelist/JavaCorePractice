package salad_leaf.jdbc_practice.entities;

import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("books")
@Getter
@Setter
public class Book {
    @Id
    private UUID id;
    private String title;
    private String author;

    @Column("publication_Year")
    private int publicationYear;
    public Book(UUID id, String title, String author, int publicationYear){
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public Book(){
        id = UUID.randomUUID();
    }
}
