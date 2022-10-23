package cucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.dbs.biblio.gestbiblio.application.port.in.PrendreEnCompteUnEmpruntUseCase;
import org.dbs.biblio.gestbiblio.application.port.out.AdherentRepository;
import org.dbs.biblio.gestbiblio.application.port.out.EmpruntRepository;
import org.dbs.biblio.gestbiblio.application.port.out.ExemplaireRepository;
import org.dbs.biblio.gestbiblio.application.service.EmpruntService;
import org.dbs.biblio.gestbiblio.domain.Adherent;
import org.dbs.biblio.gestbiblio.domain.Exemplaire;
import org.dbs.biblio.gestbiblio.domain.Livre;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EmpruntSteps {

    @Autowired
    @Qualifier("EmpruntRepositoryStub")
    private EmpruntRepository empruntRepository;
    @Mock
    private AdherentRepository adherentRepository;
    @Mock
    private ExemplaireRepository exemplaireRepository;
    @InjectMocks
    private EmpruntService empruntService;

    private Exemplaire exemplaire;
    private Livre livre;
    private Adherent adherent;
    private AutoCloseable closeable;

    @Before
    public void setup() {
        this.closeable = MockitoAnnotations.openMocks(this);
        this.empruntService = new EmpruntService(empruntRepository, adherentRepository, exemplaireRepository);
    }

    @After
    public void close() throws Exception {
        this.closeable.close();
    }

    @Given("L'exemplaire avec le code {string} est disponible")
    public void l_exemplaire_avec_le_code_est_disponible(String identifiant) {
        this.livre = Livre.builder().build();
        this.exemplaire = new Exemplaire(this.livre, identifiant);

        Mockito
                .when(exemplaireRepository.findByIdent(identifiant))
                .thenReturn(this.exemplaire);
    }

    @Given("L'adherent {string} est connue de la Bibliotheque")
    public void l_adherent_est_connue_de_la_bibliotheque(String identifiant) {
        this.adherent = new Adherent(identifiant, "Jhon", "Doe");
        Mockito
                .when(adherentRepository.findByIdent(identifiant))
                .thenReturn(this.adherent);
    }

    @When("l'adherent {string}  umprunte l'exemplaire {string}")
    public void l_adherent_umprunte_l_exemplaire(String idAdherent, String idExemplaire) {
        empruntService.prendreEnCompteUnEmprunt(new PrendreEnCompteUnEmpruntUseCase.CreationEmpruntCmd(idAdherent, idExemplaire));
    }

    @Then("l'emprunt de l'exemplaire {string} par l'adherent {string} existe")
    public void l_emprunt_de_l_exemplaire_par_l_adherent_existe(String idExemplaire, String idAdherent) {
        assertThat(((EmpruntRepositoryStub)empruntRepository).disposeOfEmprunt(idAdherent, idExemplaire)).isTrue();
    }

    @And("l'adherent {string} a emprunte {string}")
    public void lAdherentAEmprunte(String idAdherent, String idExemplaire) {
        assertThat(this.adherent.is(idAdherent)).isTrue();
        assertThat(this.adherent.aEmprunte(idExemplaire)).isTrue();
    }

    @And("l exemplaire {string} n est plus disponible")
    public void lExemplaireNEstPlusDisponible(String idExemplaire) {
        assertThat(this.exemplaire.is(idExemplaire)).isTrue();
        assertThat(this.exemplaire.isAvailable()).isFalse();
    }
}
