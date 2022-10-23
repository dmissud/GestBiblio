package org.dbs.biblio.gestbiblio.application.port.out;

import org.dbs.biblio.gestbiblio.domain.Exemplaire;

public interface ExemplaireRepository {
    Exemplaire findByIdent(String identifiant);
}
