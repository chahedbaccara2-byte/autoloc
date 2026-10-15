package tn.esprit.autoloc.autolocapi.service;


import java.util.List;
import tn.esprit.autoloc.autolocapi.domaine.Paiement;

public interface IPaiementService {
    Paiement findById(Long id);
    List<Paiement> findAll();
}
