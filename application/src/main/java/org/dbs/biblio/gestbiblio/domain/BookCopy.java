package org.dbs.biblio.gestbiblio.domain;

public class BookCopy {
    private final Book book;
    private final String identifiant;
    private Boolean available;

    public BookCopy(Book book, String identifiant) {
        this.book = book;
        this.identifiant = identifiant;
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean is(String idCopy) {
        return this.identifiant.equals(idCopy);
    }

    public void noMoreAvailable() {
        this.available = false;
    }
}
