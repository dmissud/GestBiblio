package org.dbs.biblio.gestbiblio.domain;

public class Emprunt {
    private final Exemplaire exemplaire;
    private final Adherent adherent;

    public Emprunt(Adherent adherent, Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
        this.exemplaire.noMoreAvailable();
        this.adherent = adherent;
        this.adherent.haveDo(this);
    }

    public boolean isOn(String idExemplaire) {
        return this.exemplaire.is(idExemplaire);
    }

    public boolean isBy(String idAdherent) {
        return this.adherent.is(idAdherent);
    }
}
