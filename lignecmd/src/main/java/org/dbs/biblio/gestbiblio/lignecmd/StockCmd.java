package org.dbs.biblio.gestbiblio.lignecmd;

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
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@ShellComponent
@Slf4j
public class StockCmd {
    private final Random random = new Random();
    private final BookEntityJpa bookEntityJpa;
    private final IsbnStockRepository isbnStockRepository;

    private final BiblioEntityJpa biblioEntityJpa;

    public StockCmd(BiblioEntityJpa biblioEntityJpa, IsbnStockRepository isbnStockRepository, BookEntityJpa bookEntityJpa) {
        this.bookEntityJpa = bookEntityJpa;
        this.isbnStockRepository = isbnStockRepository;
        this.biblioEntityJpa = biblioEntityJpa;
    }

    @ShellMethod("Load Book on  Stock from isbn.")
    @Transactional
    public String loadBook(int numberOfBook) {
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

        return "Create " + nbBook + " new books for all " + bookEntityJpa.count() + " Books ";
    }

    @ShellMethod("Buy Copy for Book from Stock.")
    @Transactional
    public String buyBook(int numberOfBook) {
        long sizeOfStock = this.bookEntityJpa.count();

        int pos = 0;
        Optional<BiblioEntity> biblio = biblioEntityJpa.findById("Versailles");
        if (biblio.isPresent()) {
            while (numberOfBook > pos++) {
                int position = this.random.nextInt((int) sizeOfStock);
                Pageable pageRequest = PageRequest.of(position, 1);
                BookEntity bookEntity = bookEntityJpa.findAll(pageRequest).toList().get(0);
                int numberOfCopy = this.random.nextInt(3) + 1;
                int nb = 0;
                while (numberOfCopy > nb++) {
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

        return "Done";
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
