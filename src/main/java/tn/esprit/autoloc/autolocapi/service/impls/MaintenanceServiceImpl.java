package tn.esprit.autoloc.autolocapi.service.impls;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domaine.Maintenance;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IMaintenanceRepository;
import tn.esprit.autoloc.autolocapi.service.IMaintenanceService;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements IMaintenanceService {

    private final IMaintenanceRepository maintenanceRepository;

    @Override
    public Maintenance create(Maintenance entite) {
        verifier(entite);
        if (entite.getIdMaintenance() != null) {
            throw new IllegalArgumentException(
                    "Nouvelle maintenance sans identifiant");
        }
        return maintenanceRepository.save(entite);
    }

    @Override
    public Maintenance findById(Long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Maintenance", id));
    }

    @Override
    public List<Maintenance> findAll() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance update(Long id, Maintenance entite) {
        verifier(entite);
        Maintenance existante = findById(id);
        existante.setDateDebut(entite.getDateDebut());
        existante.setDateFin(entite.getDateFin());
        existante.setDescription(entite.getDescription());
        return maintenanceRepository.save(existante);
    }

    @Override
    public void deleteById(Long id) {
        maintenanceRepository.delete(findById(id));
    }

    private void verifier(Maintenance entite) {
        if (entite == null) {
            throw new IllegalArgumentException("La maintenance est obligatoire");
        }

        if (entite.getDateDebut() != null && entite.getDateFin() != null
                && entite.getDateFin().isBefore(entite.getDateDebut())) {
            throw new IllegalArgumentException(
                    "La date de fin ne peut pas précéder la date de début");
        }
    }
}

