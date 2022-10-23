package org.dbs.biblio.gestbiblio.domain;

public class Exemplaire {
    private Livre livre;
    private String identifiant;
    private Boolean available;

    public Exemplaire(Livre livre, String identifiant) {
        this.livre = livre;
        this.identifiant = identifiant;
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean is(String idExemplaire) {
        return this.identifiant.equals(idExemplaire);
    }

    public void noMoreAvailable() {
        this.available = false;
    }
}
