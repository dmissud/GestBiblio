package org.dbs.biblio.gestbiblio.librarian;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.dbs.biblio.gestbiblio.domain.Borrow;
import org.dbs.biblio.gestbiblio.port.in.ConsiderBorrowingABook;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/documentalist")
@Tag(name = "Borrows", description = "Endpoints related to borrows operations")
public class BorrowResource {

    private final ConsiderBorrowingABook considerBorrowingABook;

    public BorrowResource(ConsiderBorrowingABook considerBorrowingABook) {
        this.considerBorrowingABook = considerBorrowingABook;
    }

    @PostMapping("/borrows/member/{memberId}/bookCopy/{bookCopyId}/create")
    public ResponseEntity<EntityModel<Borrow>> borrowABook(@PathVariable String bookCopyId, @PathVariable String memberId) {
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