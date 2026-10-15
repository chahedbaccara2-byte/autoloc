package tn.esprit.autoloc.autolocapi.service.impls;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domaine.Reservation;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IReservationRepository;
import tn.esprit.autoloc.autolocapi.service.IReservationService;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final IReservationRepository reservationRepository;

    @Override
    public Reservation create(Reservation entite) {
        verifier(entite);
        if (entite.getIdReservation() != null) {
            throw new IllegalArgumentException(
                    "Nouvelle réservation sans identifiant");
        }
        return reservationRepository.save(entite);
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reservation", id));
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation update(Long id, Reservation entite) {
        verifier(entite);
        Reservation existante = findById(id);
        existante.setDateDebut(entite.getDateDebut());
        existante.setDateFin(entite.getDateFin());
        existante.setStatut(entite.getStatut());
        return reservationRepository.save(existante);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.delete(findById(id));
    }

    private void verifier(Reservation entite) {
        if (entite == null) {
            throw new IllegalArgumentException("La réservation est obligatoire");
        }

        if (entite.getDateDebut() == null || entite.getDateFin() == null) {
            throw new IllegalArgumentException(
                    "Les dates de début et de fin sont obligatoires");
        }

        if (entite.getDateFin().isBefore(entite.getDateDebut())) {
            throw new IllegalArgumentException(
                    "La date de fin doit être postérieure ou égale à la date de début");
        }
    }
}
