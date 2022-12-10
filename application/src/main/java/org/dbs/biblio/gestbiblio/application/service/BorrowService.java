package org.dbs.biblio.gestbiblio.application.service;

import lombok.extern.slf4j.Slf4j;
import org.dbs.biblio.gestbiblio.application.port.in.ConsiderBorrowingABook;
import org.dbs.biblio.gestbiblio.application.port.out.BorrowRepository;
import org.dbs.biblio.gestbiblio.application.port.out.CopyRepository;
import org.dbs.biblio.gestbiblio.application.port.out.MemberRepository;
import org.dbs.biblio.gestbiblio.domain.BookCopy;
import org.dbs.biblio.gestbiblio.domain.Borrow;
import org.dbs.biblio.gestbiblio.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
@Slf4j
public class BorrowService implements ConsiderBorrowingABook {

    private final BorrowRepository borrowRepository;
    private final MemberRepository memberRepository;
    private final CopyRepository copyRepository;

    @Autowired
    public BorrowService(BorrowRepository borrowRepository, MemberRepository memberRepository, CopyRepository copyRepository) {
        this.borrowRepository = borrowRepository;
        this.memberRepository = memberRepository;
        this.copyRepository = copyRepository;
    }

    @Override
    public void considerBorrowingABook(CreateBorrowCmd createBorrowCmd) {
        Member member = memberRepository.findMemberByIdent(createBorrowCmd.getIdMember());
        BookCopy bookCopy = copyRepository.findCopyByIdent(createBorrowCmd.getIdCopy());
        Borrow borrow = Borrow.borrowABook(member, bookCopy);
        borrowRepository.storeBorrow(borrow);
    }
}
