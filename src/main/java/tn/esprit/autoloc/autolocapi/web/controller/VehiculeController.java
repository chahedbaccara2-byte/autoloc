package tn.esprit.autoloc.autolocapi.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domaine.Vehicule;
import tn.esprit.autoloc.autolocapi.service.IVehiculeService;
import java.util.List;

@RestController
@RequestMapping("/vehicules")
@RequiredArgsConstructor
@Tag(name = "Vehicules", description = "Gestion des vehicules")
public class VehiculeController {
    private final IVehiculeService vehiculeService;

    @GetMapping
    @Operation(summary = "Lister les vehicules")
    @ApiResponse(responseCode = "200", description = "Liste retournée")
    public List<Vehicule> findAll() {
        return vehiculeService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un(e) vehicule")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ressource trouvée"),
            @ApiResponse(responseCode = "404", description = "Ressource introuvable")
    })
    public Vehicule findById(@PathVariable Long id) {
        return vehiculeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un(e) vehicule")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Ressource créée"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    public Vehicule create(@RequestBody Vehicule entity) {
        return vehiculeService.create(entity);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un(e) vehicule")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ressource modifiée"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Ressource introuvable")
    })
    public Vehicule update(@PathVariable Long id, @RequestBody Vehicule entity) {
        return vehiculeService.update(id, entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un(e) vehicule")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Ressource supprimée"),
            @ApiResponse(responseCode = "404", description = "Ressource introuvable")
    })
    public void deleteById(@PathVariable Long id) {
        vehiculeService.deleteById(id);
    }
}
