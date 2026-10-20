package tn.esprit.autoloc.autolocapi.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.autolocapi.domaine.Reservation;
import tn.esprit.autoloc.autolocapi.service.IReservationService;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
@Tag(name = "Reservations", description = "Gestion des reservations")
public class ReservationController {
    private final IReservationService reservationService;

    @GetMapping
    @Operation(summary = "Lister les reservations")
    @ApiResponse(responseCode = "200", description = "Liste retournée")
    public List<Reservation> findAll() {
        return reservationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un(e) reservation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ressource trouvée"),
            @ApiResponse(responseCode = "404", description = "Ressource introuvable")
    })
    public Reservation findById(@PathVariable Long id) {
        return reservationService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Créer un(e) reservation")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Ressource créée"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    public Reservation create(@RequestBody Reservation entity) {
        return reservationService.create(entity);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un(e) reservation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ressource modifiée"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "404", description = "Ressource introuvable")
    })
    public Reservation update(@PathVariable Long id, @RequestBody Reservation entity) {
        return reservationService.update(id, entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Supprimer un(e) reservation")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Ressource supprimée"),
            @ApiResponse(responseCode = "404", description = "Ressource introuvable")
    })
    public void deleteById(@PathVariable Long id) {
        reservationService.deleteById(id);
    }
}
