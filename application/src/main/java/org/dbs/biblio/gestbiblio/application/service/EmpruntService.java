package org.dbs.biblio.gestbiblio.application.service;

import org.dbs.biblio.gestbiblio.application.port.in.PrendreEnCompteUnEmpruntUseCase;
import org.dbs.biblio.gestbiblio.application.port.out.AdherentRepository;
import org.dbs.biblio.gestbiblio.application.port.out.EmpruntRepository;
import org.dbs.biblio.gestbiblio.application.port.out.ExemplaireRepository;

public class EmpruntService implements PrendreEnCompteUnEmpruntUseCase {
    public EmpruntService(EmpruntRepository empruntRepository, AdherentRepository adherentRepository, ExemplaireRepository exemplaireRepository) {

    }

    @Override
    public void prendreEnCompteUnEmprunt(CreationEmpruntCmd creationEmpruntCmd) {

    }
}
