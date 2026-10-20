package tn.esprit.autoloc.autolocapi.web.controller;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domaine.Client;
import tn.esprit.autoloc.autolocapi.service.IClientService;
import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Tag(name = "Clients", description = "Gestion des clients")
public class ClientController {
    private final IClientService clientService;

    @GetMapping
    @Operation(summary = "Lister les clients")
    @ApiResponse(responseCode = "200", description = "Liste retournée")
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un(e) client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ressource trouvée"),
            @ApiResponse(responseCode = "404", description = "Ressource introuvable")
    })
    public Client findById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un(e) client")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Ressource créée"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    public Client create(@RequestBody Client entity) {
        return clientService.create(entity);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un(e) client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ressource modifiée"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Ressource introuvable")
    })
    public Client update(@PathVariable Long id, @RequestBody Client entity) {
        return clientService.update(id, entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un(e) client")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Ressource supprimée"),
            @ApiResponse(responseCode = "404", description = "Ressource introuvable")
    })
    public void deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
    }
}
