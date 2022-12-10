package org.dbs.biblio.gestbiblio.application.port.out;

import org.dbs.biblio.gestbiblio.domain.Borrow;

public interface BorrowRepository {
    void storeBorrow(Borrow borrow);

}
