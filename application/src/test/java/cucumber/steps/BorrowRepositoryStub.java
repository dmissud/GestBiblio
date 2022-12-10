package cucumber.steps;

import org.dbs.biblio.gestbiblio.application.port.out.BorrowRepository;
import org.dbs.biblio.gestbiblio.domain.Borrow;
import org.springframework.stereotype.Component;

@Component("BorrowRepositoryStub")
public class BorrowRepositoryStub implements BorrowRepository {
    private Borrow borrow = null;

    @Override
    public void storeBorrow(Borrow borrow) {
        this.borrow = borrow;
    }

    public boolean disposeOfEmprunt(String idAdherent, String idExemplaire) {
        if (borrow != null) {
            return (borrow.isBy(idAdherent) && borrow.isOn(idExemplaire));
        }
        return false;
    }

}
