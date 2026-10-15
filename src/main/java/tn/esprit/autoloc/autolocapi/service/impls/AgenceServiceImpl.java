package tn.esprit.autoloc.autolocapi.service.impls;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domaine.Agence;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IAgenceRepository;
import tn.esprit.autoloc.autolocapi.service.IAgenceService;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;

    @Override
    public Agence create(Agence entite) {
        verifier(entite);
        if (entite.getIdAgence() != null) {
            throw new IllegalArgumentException("Nouvelle agence sans identifiant");
        }
        return agenceRepository.save(entite);
    }

    @Override
    public Agence findById(Long id) {
        return agenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", id));
    }

    @Override
    public List<Agence> findAll() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence update(Long id, Agence entite) {
        verifier(entite);
        Agence existante = findById(id);
        existante.setNom(entite.getNom());
        existante.setVille(entite.getVille());
        existante.setAdresse(entite.getAdresse());
        existante.setTelephone(entite.getTelephone());
        return agenceRepository.save(existante);
    }

    @Override
    public void deleteById(Long id) {
        agenceRepository.delete(findById(id));
    }

    private void verifier(Agence entite) {
        if (entite == null || entite.getNom() == null
                || entite.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom de l'agence est obligatoire");
        }
    }
}

