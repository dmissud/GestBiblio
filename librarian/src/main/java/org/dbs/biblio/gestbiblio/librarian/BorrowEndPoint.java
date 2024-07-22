package org.dbs.biblio.gestbiblio.librarian;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.dbs.biblio.gestbiblio.domain.Borrow;
import org.dbs.biblio.gestbiblio.port.in.ConsiderBorrowingABook;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/librarian")
@Tag(name = "Borrows", description = "Endpoints related to borrows operations")
public class BorrowEndPoint {

    private final ConsiderBorrowingABook considerBorrowingABook;

    public BorrowEndPoint(ConsiderBorrowingABook considerBorrowingABook) {
        this.considerBorrowingABook = considerBorrowingABook;
    }

    @PostMapping("/borrows")
    public ResponseEntity<EntityModel<Borrow>> borrowABook(@RequestParam String memberId, @RequestParam String bookCopyId) {
        Borrow borrow = this.considerBorrowingABook.considerBorrowingABook(ConsiderBorrowingABook.CreateBorrowCmd.builder()
                .idMember(memberId)
                .idCopy(bookCopyId)
                .build());
        if (borrow == null) {
            return ResponseEntity.notFound().build();
        }

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).borrowABook(memberId, bookCopyId)).withSelfRel();
        //Link allLoansLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllLoans(memberId)).withRel("allLoans");

        EntityModel<Borrow> resource = EntityModel.of(borrow, selfLink);

        return ResponseEntity.ok(resource);
    }
}