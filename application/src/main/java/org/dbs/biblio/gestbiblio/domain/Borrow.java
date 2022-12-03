package org.dbs.biblio.gestbiblio.domain;

public class Borrow {
    private final BookCopy bookCopy;
    private final Member adherent;

    public Borrow(Member member, BookCopy bookCopy) {
        this.bookCopy = bookCopy;
        this.bookCopy.noMoreAvailable();
        this.adherent = member;
        this.adherent.haveDo(this);
    }

    public boolean isOn(String idExemplaire) {
        return this.bookCopy.is(idExemplaire);
    }

    public boolean isBy(String idAdherent) {
        return this.adherent.is(idAdherent);
    }

    public String giveNameOfMember() { return adherent.fullName();}

    public String giveCopyBookDescription() {
        return bookCopy.giveDescription();
    }
}
