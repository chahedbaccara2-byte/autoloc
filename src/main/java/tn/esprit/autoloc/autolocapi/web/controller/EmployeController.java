package tn.esprit.autoloc.autolocapi.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domaine.Employe;
import tn.esprit.autoloc.autolocapi.service.IEmployeService;
import java.util.List;

@RestController
@RequestMapping("/employes")
@RequiredArgsConstructor
@Tag(name = "Employés", description = "Gestion des employés")
public class EmployeController {
    private final IEmployeService employeService;

    @GetMapping
    @Operation(summary = "Lister les employés")
    @ApiResponse(responseCode = "200", description = "Liste retournée")
    public List<Employe> findAll() {
        return employeService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un(e) employe")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ressource trouvée"),
            @ApiResponse(responseCode = "404", description = "Ressource introuvable")
    })
    public Employe findById(@PathVariable Long id) {
        return employeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un(e) employe")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Ressource créée"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    public Employe create(@RequestBody Employe entity) {
        return employeService.create(entity);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un(e) employe")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ressource modifiée"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Ressource introuvable")
    })
    public Employe update(@PathVariable Long id, @RequestBody Employe entity) {
        return employeService.update(id, entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un(e) employe")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Ressource supprimée"),
            @ApiResponse(responseCode = "404", description = "Ressource introuvable")
    })
    public void deleteById(@PathVariable Long id) {
        employeService.deleteById(id);
    }
}
