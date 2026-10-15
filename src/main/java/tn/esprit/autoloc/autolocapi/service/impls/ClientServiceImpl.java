package tn.esprit.autoloc.autolocapi.service.impls;


import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.autolocapi.domaine.Client;
import tn.esprit.autoloc.autolocapi.exception.ResourceNotFoundException;
import tn.esprit.autoloc.autolocapi.repository.IClientRepository;
import tn.esprit.autoloc.autolocapi.service.IClientService;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;

    @Override
    public Client create(Client entite) {
        verifier(entite);
        if (entite.getIdClient() != null) {
            throw new IllegalArgumentException("Nouveau client sans identifiant");
        }
        return clientRepository.save(entite);
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client update(Long id, Client entite) {
        verifier(entite);
        Client existant = findById(id);
        existant.setNom(entite.getNom());
        existant.setPrenom(entite.getPrenom());
        existant.setEmail(entite.getEmail());
        existant.setTelephone(entite.getTelephone());
        existant.setNumPermis(entite.getNumPermis());

        // La date d'inscription n'est pas modifiée lors d'une mise à jour.
        return clientRepository.save(existant);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.delete(findById(id));
    }

    private void verifier(Client entite) {
        if (entite == null
                || entite.getNom() == null || entite.getNom().isBlank()
                || entite.getEmail() == null || entite.getEmail().isBlank()) {
            throw new IllegalArgumentException(
                    "Le nom et l'e-mail du client sont obligatoires");
        }
    }
}

