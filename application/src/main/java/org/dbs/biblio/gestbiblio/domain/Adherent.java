package org.dbs.biblio.gestbiblio.domain;

import lombok.Builder;

@Builder
public class Adherent {
    private String indentifiant;
    private String nom;
    private String prenom;

    public boolean aEmprunte(String idExemplaire) {
        return false;
    }
}
