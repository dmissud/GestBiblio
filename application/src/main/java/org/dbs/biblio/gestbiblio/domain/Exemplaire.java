package org.dbs.biblio.gestbiblio.domain;

import lombok.Builder;

@Builder
public class Exemplaire {
    private Livre livre;
    private String identifiant;

    public boolean isDisponible() {
        return false;
    }

    public boolean is(String idExemplaire) {
        return false;
    }
}
