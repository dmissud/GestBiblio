package org.dbs.biblio.gestbiblio.librarian;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.dbs.biblio.gestbiblio.infrastructure.config.BiblioEntity;
import org.dbs.biblio.gestbiblio.infrastructure.config.BiblioEntityJpa;
import org.dbs.biblio.gestbiblio.infrastructure.isbn.IsbnEntity;
import org.dbs.biblio.gestbiblio.infrastructure.isbn.IsbnStockRepository;
import org.dbs.biblio.gestbiblio.infrastructure.stock.BookCopyEntity;
import org.dbs.biblio.gestbiblio.infrastructure.stock.BookEntity;
import org.dbs.biblio.gestbiblio.infrastructure.stock.BookEntityJpa;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/librarian")
@Tag(name = "Books", description = "Endpoints related to stock's operations")
@Slf4j
public class BooksResource {
    private final Random random = new Random();
    private final BookEntityJpa bookEntityJpa;
    private final IsbnStockRepository isbnStockRepository;

    private final BiblioEntityJpa biblioEntityJpa;

    public BooksResource(BookEntityJpa bookEntityJpa, IsbnStockRepository isbnStockRepository, BiblioEntityJpa biblioEntityJpa) {
        this.bookEntityJpa = bookEntityJpa;
        this.isbnStockRepository = isbnStockRepository;
        this.biblioEntityJpa = biblioEntityJpa;
    }

    @PostMapping("/books/reference/{numberOfBook}")
    @Transactional
    public ResponseEntity<Integer> referenceSomeBook(@PathVariable int numberOfBook) {
        long sizeOfStock = this.isbnStockRepository.sizeOfStock();

        int pos = 0;
        int nbBook = 0;

        while (numberOfBook > pos++) {
            int position = this.random.nextInt((int) sizeOfStock);
            IsbnEntity isbnEntity = isbnStockRepository.findIsbnsByPosition(position);
            Optional<BookEntity> searchBookEntity = bookEntityJpa.findByIsbn13(isbnEntity.getIsbn13());
            if (searchBookEntity.isEmpty()) {
                BookEntity bookEntity = new BookEntity();
                bookEntity.setIsbn13(isbnEntity.getIsbn13());
                bookEntity.setTitre(isbnEntity.getTitre());
                nbBook++;
                bookEntityJpa.save(bookEntity);
                log.info("Livre: {} is create", isbnEntity);
            }
            log.info("Livre: {} exist", isbnEntity);
        }
        log.info("Create {} new books for all {} Books ", nbBook, bookEntityJpa.count());

        return ResponseEntity.ok(nbBook);
    }

    @PostMapping("/books/buy/{numberOfBook}")
    @Transactional
    public ResponseEntity<Integer> buySomeBookCopy(@RequestParam int numberOfBook) {
        long sizeOfStock = this.bookEntityJpa.count();

        int pos = 0;
        int nbOfCopyBought = 0;
        Optional<BiblioEntity> biblio = biblioEntityJpa.findById("Versailles");
        if (biblio.isPresent()) {
            while (numberOfBook > pos++) {
                int position = this.random.nextInt((int) sizeOfStock);
                Pageable pageRequest = PageRequest.of(position, 1);
                BookEntity bookEntity = bookEntityJpa.findAll(pageRequest).toList().get(0);
                int numberOfCopy = this.random.nextInt(3) + 1;
                int nb = 0;
                while (numberOfCopy > nb++) {
                    nbOfCopyBought++;
                    BookCopyEntity bookCopyEntity = new BookCopyEntity();
                    bookCopyEntity.setAvailable(true);
                    bookCopyEntity.setBookEntity(bookEntity);
                    bookCopyEntity.setIdentifiant(incIdent(biblio));
                    bookEntity.getBookCopyEntities().add(bookCopyEntity);
                }
                bookEntityJpa.save(bookEntity);
            }
            biblioEntityJpa.save(biblio.get());
        }

        return ResponseEntity.ok(nbOfCopyBought);
    }

    private String incIdent(Optional<BiblioEntity> biblio) {
        if (biblio.isPresent()) {
            String identifiant = biblio.get().getCode()+String.format("%08d", biblio.get().getLastCopyBookIdentifiant());
            biblio.get().setLastCopyBookIdentifiant(biblio.get().getLastCopyBookIdentifiant()+1);
            return identifiant;
        } else {
            return "NoId";
        }
    }
}