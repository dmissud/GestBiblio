package org.dbs.biblio.gestbiblio.infrastructure.stock;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookEntityJpa extends JpaRepository<BookEntity, UUID> {
    Optional<BookEntity> findByIsbn13(String isbn13);
}
