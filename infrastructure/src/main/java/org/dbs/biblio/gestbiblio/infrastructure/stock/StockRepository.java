package org.dbs.biblio.gestbiblio.infrastructure.stock;

import lombok.extern.slf4j.Slf4j;
import org.dbs.biblio.gestbiblio.domain.BookCopy;
import org.dbs.biblio.gestbiblio.domain.Borrow;
import org.dbs.biblio.gestbiblio.domain.Member;
import org.dbs.biblio.gestbiblio.port.out.BorrowRepository;
import org.dbs.biblio.gestbiblio.port.out.CopyRepository;
import org.dbs.biblio.gestbiblio.port.out.MemberRepository;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class StockRepository implements BorrowRepository, CopyRepository, MemberRepository {
    @Override
    public void storeBorrow(Borrow borrow) {
        log.debug("StockRepository.storeBorrow is empty");
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
