package org.dbs.biblio.gestbiblio.application.port.out;

import org.dbs.biblio.gestbiblio.domain.Adherent;

public interface AdherentRepository {
    Adherent findByIdent(String identifiant);
}
