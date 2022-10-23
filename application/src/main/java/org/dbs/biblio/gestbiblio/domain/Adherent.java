package org.dbs.biblio.gestbiblio.domain;

import java.util.ArrayList;
import java.util.List;

public class Adherent {
    private final String identifiant;
    private final String nom;
    private final String prenom;
    private List<Emprunt> emprunts;

    public Adherent(String identifiant, String nom, String prenom) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
        this.emprunts = new ArrayList<>();
    }

    public boolean aEmprunte(String idExemplaire) {
        return this.emprunts.stream()
                .anyMatch(emprunt -> emprunt.isOn(idExemplaire));
    }

    public boolean is(String idAdherent) {
        return identifiant.equals(idAdherent);
    }

    public void haveDo(Emprunt emprunt) {
        this.emprunts.add(emprunt);
    }
}
