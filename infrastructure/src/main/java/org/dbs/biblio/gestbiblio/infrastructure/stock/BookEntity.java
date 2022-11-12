package org.dbs.biblio.gestbiblio.infrastructure.stock;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "book_entity",
        indexes = {
        @Index(name = "isbn_idx", columnList = "isbn13", unique = true)
})
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String isbn13;

    private String titre;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<BookCopyEntity> bookCopyEntities = new LinkedHashSet<>();

    public Set<BookCopyEntity> getBookCopyEntities() {
        return bookCopyEntities;
    }

    public void setBookCopyEntities(Set<BookCopyEntity> bookCopyEntities) {
        this.bookCopyEntities = bookCopyEntities;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getIsbn13() {
        return isbn13;
    }
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
}
