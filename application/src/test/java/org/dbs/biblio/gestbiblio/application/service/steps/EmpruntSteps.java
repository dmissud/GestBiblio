package org.dbs.biblio.gestbiblio.application.service.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.dbs.biblio.gestbiblio.domain.Adherent;
import org.dbs.biblio.gestbiblio.domain.Exemplaire;
import org.dbs.biblio.gestbiblio.domain.Livre;


public class EmpruntSteps {

    private Exemplaire exemplaire;
    private Livre livre;
    private Adherent adherent;

    @Given("L'exemplaire avec le code {string} est disponible")
    public void l_exemplaire_avec_le_code_est_disponible(String identifiant) {
        this.livre = Livre.builder().build();
        this.exemplaire = Exemplaire.builder()
                .livre(this.livre)
                .identifiant(identifiant)
                .build();
    }

    @Given("L'adherent {string} est connue de la Bibliotheque")
    public void l_adherent_est_connue_de_la_bibliotheque(String identifiant) {
        this.adherent = Adherent.builder()
                .indentifiant(identifiant)
                .build();
    }

    @When("l'adherent {string}  umprunte l'exemplaire {string}")
    public void l_adherent_umprunte_l_exemplaire(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("l'emprunt est cree")
    public void l_emprunt_est_cree() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("l'exemplaire n'est plus disponible")
    public void l_exemplaire_n_est_plus_disponible() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("l'adherent dispose d'un emprunt en plus")
    public void l_adherent_dispose_d_un_emprunt_en_plus() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
