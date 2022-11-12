package org.dbs.biblio.gestbiblio.infrastructure.isbn;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IsbnEntityJpa extends JpaRepository<IsbnEntity, String> {
}
