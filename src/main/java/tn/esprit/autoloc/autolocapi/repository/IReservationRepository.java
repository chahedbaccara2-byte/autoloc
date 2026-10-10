package tn.esprit.autoloc.autolocapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.autolocapi.domaine.Reservation;

public interface IReservationRepository
        extends JpaRepository<Reservation, Long> {
}