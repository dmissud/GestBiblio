package org.dbs.biblio.gestbiblio.infrastructure.config;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BiblioEntityJpa extends JpaRepository<BiblioEntity, String> {
}
