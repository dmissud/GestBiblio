package org.dbs.biblio.gestbiblio.domain;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private final String identifiant;
    private final String name;
    private final String firstName;
    private final List<Borrow> borrows;

    public Member(String identifiant, String name, String firstName) {
        this.identifiant = identifiant;
        this.name = name;
        this.firstName = firstName;
        this.borrows = new ArrayList<>();
    }

    public boolean aEmprunte(String idExemplaire) {
        return this.borrows.stream()
                .anyMatch(emprunt -> emprunt.isOn(idExemplaire));
    }

    public boolean is(String idAdherent) {
        return identifiant.equals(idAdherent);
    }

    public void haveDo(Borrow borrow) {
        this.borrows.add(borrow);
    }
}
