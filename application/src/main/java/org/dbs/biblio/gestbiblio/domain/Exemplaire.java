package org.dbs.biblio.gestbiblio.domain;

import lombok.Builder;

@Builder
public class Exemplaire {
    private Livre livre;
    private String identifiant;
}
