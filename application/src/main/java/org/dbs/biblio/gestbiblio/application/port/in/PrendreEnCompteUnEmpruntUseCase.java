package org.dbs.biblio.gestbiblio.application.port.in;

import lombok.Getter;
import org.dbs.biblio.gestbiblio.application.common.SelfValidating;

import javax.validation.constraints.Pattern;

public interface PrendreEnCompteUnEmpruntUseCase {
    void prendreEnCompteUnEmprunt(CreationEmpruntCmd creationEmpruntCmd);


    @Getter
    class CreationEmpruntCmd extends SelfValidating<CreationEmpruntCmd> {
        @Pattern(regexp = "^(AD\\d{5})$")
        private final String idAdherent;

        @Pattern(regexp = "^(EX\\d{5})$")
        private final String idExemplaire;

        public CreationEmpruntCmd(String idAdherent, String idExemplaire) {
            this.idAdherent = idAdherent;
            this.idExemplaire = idExemplaire;
            this.validateSelf();
        }
    }
}
