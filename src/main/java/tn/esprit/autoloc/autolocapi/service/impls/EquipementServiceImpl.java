package tn.esprit.autoloc.autolocapi.service.impls;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domaine.Equipement;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IEquipementRepository;
import tn.esprit.autoloc.autolocapi.service.IEquipementService;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements IEquipementService {

    private final IEquipementRepository equipementRepository;

    @Override
    public Equipement create(Equipement entite) {
        verifier(entite);
        if (entite.getIdEquipement() != null) {
            throw new IllegalArgumentException(
                    "Nouvel équipement sans identifiant");
        }
        return equipementRepository.save(entite);
    }

    @Override
    public Equipement findById(Long id) {
        return equipementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement", id));
    }

    @Override
    public List<Equipement> findAll() {
        return equipementRepository.findAll();
    }

    @Override
    public Equipement update(Long id, Equipement entite) {
        verifier(entite);
        Equipement existant = findById(id);
        existant.setLibelle(entite.getLibelle());
        return equipementRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        equipementRepository.delete(findById(id));
    }

    private void verifier(Equipement entite) {
        if (entite == null || entite.getLibelle() == null
                || entite.getLibelle().isBlank()) {
            throw new IllegalArgumentException(
                    "Le libellé de l'équipement est obligatoire");
        }
    }
}
