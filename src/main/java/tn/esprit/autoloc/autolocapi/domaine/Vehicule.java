package tn.esprit.autoloc.autolocapi.domaine;

import jakarta.persistence.*;
import lombok.*;
@Table(name = "vehicule")
@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicule;

    @Column(nullable = false, unique = true, length = 20)
    private String immatriculation;

    @Column(nullable = false, length = 50)
    private String marque;

    @Column(nullable = false, length = 50)
    private String modele;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CategorieVehicule categorie;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutVehicule statut;



}
