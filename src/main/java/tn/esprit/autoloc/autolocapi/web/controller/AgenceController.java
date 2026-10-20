package tn.esprit.autoloc.autolocapi.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domaine.Agence;
import tn.esprit.autoloc.autolocapi.service.IAgenceService;
import java.util.List;

@RestController
@RequestMapping("/agences")
@RequiredArgsConstructor
@Tag(name = "Agences", description = "Gestion des agences")
public class AgenceController {
    private final IAgenceService agenceService;

    @GetMapping
    @Operation(summary = "Lister les agences")
    @ApiResponse(responseCode = "200", description = "Liste retournée")
    public List<Agence> findAll() {
        return agenceService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un(e) agence")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ressource trouvée"),
            @ApiResponse(responseCode = "404", description = "Ressource introuvable")
    })
    public Agence findById(@PathVariable Long id) {
        return agenceService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un(e) agence")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Ressource créée"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    public Agence create(@RequestBody Agence entity) {
        return agenceService.create(entity);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un(e) agence")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ressource modifiée"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Ressource introuvable")
    })
    public Agence update(@PathVariable Long id, @RequestBody Agence entity) {
        return agenceService.update(id, entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un(e) agence")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Ressource supprimée"),
            @ApiResponse(responseCode = "404", description = "Ressource introuvable")
    })
    public void deleteById(@PathVariable Long id) {
        agenceService.deleteById(id);
    }
}
