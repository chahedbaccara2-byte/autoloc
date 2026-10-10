package tn.esprit.autoloc.autolocapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.autolocapi.domaine.Contrat;

public interface IContratRepository
        extends JpaRepository<Contrat, Long> {
}