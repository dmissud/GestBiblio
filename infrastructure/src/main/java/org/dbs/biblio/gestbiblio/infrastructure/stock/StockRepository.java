package org.dbs.biblio.gestbiblio.infrastructure.stock;

import org.dbs.biblio.gestbiblio.application.port.out.MemberRepository;
import org.dbs.biblio.gestbiblio.application.port.out.BorrowRepository;
import org.dbs.biblio.gestbiblio.application.port.out.CopyRepository;
import org.dbs.biblio.gestbiblio.domain.BookCopy;
import org.dbs.biblio.gestbiblio.domain.Borrow;
import org.dbs.biblio.gestbiblio.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public class StockRepository implements BorrowRepository, CopyRepository, MemberRepository {
    @Override
    public void storeBorrow(Borrow borrow) {

    }

    @Override
    public Member findMemberByIdent(String identified) {
        return null;
    }

    @Override
    public BookCopy findCopyByIdent(String identified) {
        return null;
    }

}
