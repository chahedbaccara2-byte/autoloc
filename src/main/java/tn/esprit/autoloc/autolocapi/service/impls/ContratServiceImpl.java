package tn.esprit.autoloc.autolocapi.service.impls;


import java.util.List;

import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc.autolocapi.domaine.Contrat;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IContratRepository;
import tn.esprit.autoloc.autolocapi.service.IContratService;

@Service
@RequiredArgsConstructor

public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;

    @Override

    public Contrat create(Contrat contrat) {
        if (contrat == null) {
            throw new IllegalArgumentException("Le contrat est obligatoire");
        }
        if (contrat.getId() != 0) {
            throw new IllegalArgumentException(
                    "Un nouveau contrat ne doit pas avoir d'identifiant");
        }
        if (contrat.getMontantTotal() == null) {
            throw new IllegalArgumentException(
                    "Le montant total ne peut pas être négatif");
        }
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat findById(Long id) {
        return contratRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrat", id));
    }

    @Override
    public List<Contrat> findAll() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat update(Long id, Contrat contrat) {
        if (contrat == null) {
            throw new IllegalArgumentException("Le contrat est obligatoire");
        }

        Contrat existant = findById(id);

        if (contrat.getMontantTotal() == null) {
            throw new IllegalArgumentException(
                    "Le montant total ne peut pas être négatif");
        }

        existant.setMontantTotal(contrat.getMontantTotal());

        // Recopier ici les autres attributs modifiables de Contrat.
        // Ne pas modifier l'identifiant ni la collection des paiements.

        return contratRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        Contrat contrat = findById(id);
        contratRepository.delete(contrat);
    }
}
