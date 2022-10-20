package org.dbs.biblio.gestbiblio.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dbs.biblio.gestbiblio.application.common.SelfValidating;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;

public interface PrendreEnCompteUnEmpruntUseCase {
    void prendreEnCompteUnEmprunt(CreationEmpruntCmd creationEmpruntCmd);

    @Validated
    @Getter
    @AllArgsConstructor
    class CreationEmpruntCmd extends SelfValidating<CreationEmpruntCmd> {
        @Pattern(regexp = "^(AD[0-9]{5})$")
        private final String idAdherent;

        @Pattern(regexp = "^(EX[0-9]{5})$")
        private final String idExemplaire;
    }
}
