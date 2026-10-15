package tn.esprit.autoloc.autolocapi.service.impls;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domaine.Employe;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IEmployeRepository;
import tn.esprit.autoloc.autolocapi.service.IEmployeService;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {

    private final IEmployeRepository employeRepository;

    @Override
    public Employe create(Employe entite) {
        verifier(entite);
        if (entite.getIdEmploye() != null) {
            throw new IllegalArgumentException("Nouvel employé sans identifiant");
        }
        return employeRepository.save(entite);
    }

    @Override
    public Employe findById(Long id) {
        return employeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employe", id));
    }

    @Override
    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    @Override
    public Employe update(Long id, Employe entite) {
        verifier(entite);
        Employe existant = findById(id);
        existant.setNom(entite.getNom());
        existant.setPrenom(entite.getPrenom());
        existant.setRole(entite.getRole());
        return employeRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        employeRepository.delete(findById(id));
    }

    private void verifier(Employe entite) {
        if (entite == null || entite.getNom() == null
                || entite.getNom().isBlank()
                || entite.getRole() == null) {
            throw new IllegalArgumentException(
                    "Le nom et le rôle de l'employé sont obligatoires");
        }
    }
}

