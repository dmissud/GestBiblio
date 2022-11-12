package org.dbs.biblio.gestbiblio.lignecmd;

import lombok.extern.slf4j.Slf4j;
import org.dbs.biblio.gestbiblio.infrastructure.isbn.IsbnStockRepository;
import org.dbs.biblio.gestbiblio.infrastructure.stock.BookEntityJpa;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Random;

@ShellComponent
@Slf4j
public class IsbnCmd {
    public static final String SEPARATOR_LINE = "-------------------------------------------------\n";
    private final IsbnStockRepository isbnStockRepository;
    private final Random random = new Random();
    private final BookEntityJpa bookEntityJpa;

    public IsbnCmd(IsbnStockRepository isbnStockRepository, BookEntityJpa bookEntityJpa) {
        this.isbnStockRepository = isbnStockRepository;
        this.bookEntityJpa = bookEntityJpa;
    }

    @ShellMethod("List statitistiques of isbn.")
    public String isbnStats() {
        return buildStats();
    }

    private String buildStats() {
        String stats = SEPARATOR_LINE;
        stats += "Isbn size : " + this.isbnStockRepository.sizeOfStock() + "\n";
        stats += SEPARATOR_LINE;
        return stats;
    }
}
