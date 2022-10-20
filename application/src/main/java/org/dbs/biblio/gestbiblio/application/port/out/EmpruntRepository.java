package org.dbs.biblio.gestbiblio.application.port.out;

import org.dbs.biblio.gestbiblio.domain.Emprunt;

public interface EmpruntRepository {
    void storeEmprunt(Emprunt emprunt);
}
