package org.dbs.biblio.gestbiblio.port.out;

import org.dbs.biblio.gestbiblio.domain.BookCopy;

public interface CopyRepository {
    BookCopy findCopyByIdent(String identifiant);
}
