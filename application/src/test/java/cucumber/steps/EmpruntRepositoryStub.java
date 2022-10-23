package cucumber.steps;

import org.dbs.biblio.gestbiblio.application.port.out.EmpruntRepository;
import org.dbs.biblio.gestbiblio.domain.Emprunt;
import org.springframework.stereotype.Component;

@Component("EmpruntRepositoryStub")
public class EmpruntRepositoryStub implements EmpruntRepository {
    private Emprunt emprunt = null;

    @Override
    public void storeEmprunt(Emprunt emprunt) {
        this.emprunt = emprunt;
    }
    public boolean disposeOfEmprunt(String idAdherent, String idExemplaire) {
        if (emprunt != null) {
            return (emprunt.isBy(idAdherent) && emprunt.isOn(idExemplaire));
        }
        return false;
    }

}
