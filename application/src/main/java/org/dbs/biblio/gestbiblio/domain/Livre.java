package org.dbs.biblio.gestbiblio.domain;

import lombok.Builder;

@Builder
public class Livre {
    private String isbn10;
    private String isbn13;
    private String titre;
}
