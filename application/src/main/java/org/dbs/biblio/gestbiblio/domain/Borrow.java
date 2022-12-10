package org.dbs.biblio.gestbiblio.domain;

import lombok.extern.slf4j.Slf4j;
import org.dbs.biblio.gestbiblio.domain.exeception.BusinessException;
@Slf4j
public class Borrow {
    private final BookCopy bookCopy;
    private final Member adherent;

    private Borrow(Member member, BookCopy bookCopy) {
        this.bookCopy = bookCopy;
        this.bookCopy.noMoreAvailable();
        this.adherent = member;
        this.adherent.haveDo(this);
    }

    public static Borrow borrowABook(Member member, BookCopy bookCopy) {
        if (bookCopy.isAvailable()) {
            log.trace("Borrow of bookCopy {} by {}", bookCopy.giveDescription(), member.fullName());
            return new Borrow(member, bookCopy);
        } else {
            log.error("bookCopy {}  is not possible", bookCopy.giveDescription());
            throw new BusinessException("Le livre n'est pas disponible");
        }
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
