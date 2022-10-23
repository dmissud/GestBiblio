package org.dbs.biblio.gestbiblio.application.service;

import org.dbs.biblio.gestbiblio.application.port.in.PrendreEnCompteUnEmpruntUseCase;
import org.dbs.biblio.gestbiblio.application.port.out.AdherentRepository;
import org.dbs.biblio.gestbiblio.application.port.out.EmpruntRepository;
import org.dbs.biblio.gestbiblio.application.port.out.ExemplaireRepository;
import org.dbs.biblio.gestbiblio.domain.Adherent;
import org.dbs.biblio.gestbiblio.domain.Emprunt;
import org.dbs.biblio.gestbiblio.domain.Exemplaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class EmpruntService implements PrendreEnCompteUnEmpruntUseCase {

    private final EmpruntRepository empruntRepository;
    private final AdherentRepository adherentRepository;
    private final ExemplaireRepository exemplaireRepository;

    @Autowired
    public EmpruntService(EmpruntRepository empruntRepository, AdherentRepository adherentRepository, ExemplaireRepository exemplaireRepository) {
        this.empruntRepository = empruntRepository;
        this.adherentRepository = adherentRepository;
        this.exemplaireRepository = exemplaireRepository;
    }

    @Override
    public void prendreEnCompteUnEmprunt(CreationEmpruntCmd creationEmpruntCmd) {
        Adherent adherent = adherentRepository.findByIdent(creationEmpruntCmd.getIdAdherent());
        Exemplaire exemplaire = exemplaireRepository.findByIdent(creationEmpruntCmd.getIdExemplaire());
        Emprunt emprunt = new Emprunt(adherent, exemplaire);
        empruntRepository.storeEmprunt(emprunt);
    }
}
