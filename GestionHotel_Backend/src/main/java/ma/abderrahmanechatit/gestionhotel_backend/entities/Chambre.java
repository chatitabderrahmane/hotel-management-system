package ma.abderrahmanechatit.gestionhotel_backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.abderrahmanechatit.gestionhotel_backend.enums.ChambreEtat;
import ma.abderrahmanechatit.gestionhotel_backend.enums.ChambreType;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chambre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ImageUrl;
    private String numero;
     private  String description;
    private  String title;
    private double price;
    private ChambreEtat status;
    @ManyToOne
    private CategoryChambre type;
    @ManyToMany
    @JoinTable(
            name = "chambre_feature",
            joinColumns = @JoinColumn(name = "chambre_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id"))
    private List<Feature> features;

    @ManyToMany
    @JoinTable(
            name = "chambre_facility",
            joinColumns = @JoinColumn(name = "chambre_id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id"))
    private List<Facility> facilities;
 }
