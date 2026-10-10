package tn.esprit.autoloc.autolocapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.autolocapi.domaine.Vehicule;

public interface IVehiculeRepository
        extends JpaRepository<Vehicule, Long> {
}
