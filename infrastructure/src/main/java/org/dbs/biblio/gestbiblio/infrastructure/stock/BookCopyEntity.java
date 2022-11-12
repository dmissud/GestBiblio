package org.dbs.biblio.gestbiblio.infrastructure.stock;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "book_copy_entity")
public class BookCopyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    private boolean available;

    @Column(unique = true)
    private String identifiant;

    @ManyToOne
    @JoinColumn(name = "book_entity_id")
    private BookEntity bookEntity;

    public BookEntity getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookCopyEntity that)) return false;

        if (isAvailable() != that.isAvailable()) return false;
        return getIdentifiant().equals(that.getIdentifiant());
    }

    @Override
    public int hashCode() {
        int result = (isAvailable() ? 1 : 0);
        result = 31 * result + getIdentifiant().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BookCopyEntity{" +
                "available=" + available +
                ", identifiant='" + identifiant + '\'' +
                '}';
    }
}
