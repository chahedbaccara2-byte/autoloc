package tn.esprit.autoloc.autolocapi.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domaine.Equipement;
import tn.esprit.autoloc.autolocapi.service.IEquipementService;
import java.util.List;

@RestController
@RequestMapping("/equipements")
@RequiredArgsConstructor
@Tag(name = "Equipements", description = "Gestion des equipements")
public class EquipementController {
    private final IEquipementService equipementService;

    @GetMapping
    @Operation(summary = "Lister les equipements")
    @ApiResponse(responseCode = "200", description = "Liste retournée")
    public List<Equipement> findAll() {
        return equipementService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un(e) equipement")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ressource trouvée"),
            @ApiResponse(responseCode = "404", description = "Ressource introuvable")
    })
    public Equipement findById(@PathVariable Long id) {
        return equipementService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un(e) equipement")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Ressource créée"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    public Equipement create(@RequestBody Equipement entity) {
        return equipementService.create(entity);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un(e) equipement")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ressource modifiée"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Ressource introuvable")
    })
    public Equipement update(@PathVariable Long id, @RequestBody Equipement entity) {
        return equipementService.update(id, entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un(e) equipement")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Ressource supprimée"),
            @ApiResponse(responseCode = "404", description = "Ressource introuvable")
    })
    public void deleteById(@PathVariable Long id) {
        equipementService.deleteById(id);
    }
}

