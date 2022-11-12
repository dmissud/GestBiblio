package cucumber.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.dbs.biblio.gestbiblio.application.port.in.ConsiderBorrowingABook;
import org.dbs.biblio.gestbiblio.application.port.out.MemberRepository;
import org.dbs.biblio.gestbiblio.application.port.out.BorrowRepository;
import org.dbs.biblio.gestbiblio.application.port.out.CopyRepository;
import org.dbs.biblio.gestbiblio.application.service.BorrowService;
import org.dbs.biblio.gestbiblio.domain.Book;
import org.dbs.biblio.gestbiblio.domain.Member;
import org.dbs.biblio.gestbiblio.domain.BookCopy;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BorrowSteps {

    @Autowired
    @Qualifier("BorrowRepositoryStub")
    private BorrowRepository borrowRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private CopyRepository copyRepository;
    @InjectMocks
    private BorrowService borrowService;

    private BookCopy bookCopy;
    private Member member;
    private AutoCloseable closeable;

    @Before
    public void setup() {
        this.closeable = MockitoAnnotations.openMocks(this);
        this.borrowService = new BorrowService(borrowRepository, memberRepository, copyRepository);
    }

    @After
    public void close() throws Exception {
        this.closeable.close();
    }

    @Given("L'exemplaire avec le code {string} est disponible")
    public void l_exemplaire_avec_le_code_est_disponible(String identifiant) {
        this.bookCopy = new BookCopy(new Book(), identifiant);

        Mockito
                .when(copyRepository.findCopyByIdent(identifiant))
                .thenReturn(this.bookCopy);
    }

    @Given("L'adherent {string} est connue de la Bibliotheque")
    public void l_adherent_est_connue_de_la_bibliotheque(String identifiant) {
        this.member = new Member(identifiant, "Jhon", "Doe");
        Mockito
                .when(memberRepository.findMemberByIdent(identifiant))
                .thenReturn(this.member);
    }

    @When("l'adherent {string}  umprunte l'exemplaire {string}")
    public void l_adherent_umprunte_l_exemplaire(String idAdherent, String idExemplaire) {
        borrowService.considerBorrowingABook(new ConsiderBorrowingABook.CreateBorrowCmd(idAdherent, idExemplaire));
    }

    @Then("l'emprunt de l'exemplaire {string} par l'adherent {string} existe")
    public void l_emprunt_de_l_exemplaire_par_l_adherent_existe(String idExemplaire, String idAdherent) {
        assertThat(((BorrowRepositoryStub) borrowRepository).disposeOfEmprunt(idAdherent, idExemplaire)).isTrue();
    }

    @And("l'adherent {string} a emprunte {string}")
    public void lAdherentAEmprunte(String idAdherent, String idExemplaire) {
        assertThat(this.member.is(idAdherent)).isTrue();
        assertThat(this.member.aEmprunte(idExemplaire)).isTrue();
    }

    @And("l exemplaire {string} n est plus disponible")
    public void lExemplaireNEstPlusDisponible(String idExemplaire) {
        assertThat(this.bookCopy.is(idExemplaire)).isTrue();
        assertThat(this.bookCopy.isAvailable()).isFalse();
    }
}
