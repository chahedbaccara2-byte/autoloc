package tn.esprit.autoloc.autolocapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.autolocapi.domaine.Employe;

public interface IEmployeRepository
        extends JpaRepository<Employe, Long> {
}
