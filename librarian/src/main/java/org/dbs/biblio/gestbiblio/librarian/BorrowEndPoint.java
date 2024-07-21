package org.dbs.biblio.gestbiblio.librarian;

import org.dbs.biblio.gestbiblio.domain.Borrow;
import org.dbs.biblio.gestbiblio.port.in.ConsiderBorrowingABook;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowEndPoint {

    private final ConsiderBorrowingABook considerBorrowingABook;

    public BorrowEndPoint(ConsiderBorrowingABook considerBorrowingABook) {
        this.considerBorrowingABook = considerBorrowingABook;
    }

    @PostMapping("/loan")
    public ResponseEntity<EntityModel<Borrow>> borrowABook(@RequestParam String bookId, @RequestParam String memberId) {
        Borrow borrow = this.considerBorrowingABook.considerBorrowingABook(ConsiderBorrowingABook.CreateBorrowCmd.builder()
                .idMember(memberId)
                .idCopy(bookId)
                .build());
        if (borrow == null) {
            return ResponseEntity.notFound().build();
        }

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).borrowABook(bookId, memberId)).withSelfRel();
        //Link allLoansLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllLoans(memberId)).withRel("allLoans");

        EntityModel<Borrow> resource = EntityModel.of(borrow, selfLink);

        return ResponseEntity.ok(resource);
    }
}