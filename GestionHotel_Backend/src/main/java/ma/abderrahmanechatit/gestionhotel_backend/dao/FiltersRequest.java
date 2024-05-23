package ma.abderrahmanechatit.gestionhotel_backend.dao;

import ma.abderrahmanechatit.gestionhotel_backend.entities.CategoryChambre;
import ma.abderrahmanechatit.gestionhotel_backend.entities.Facility;
import ma.abderrahmanechatit.gestionhotel_backend.entities.Feature;
import ma.abderrahmanechatit.gestionhotel_backend.enums.ChambreEtat;

import java.util.List;

public class FiltersRequest {

    private CategoryChambre type;
    private  double MinPrix;
    private  double MaxPrix;
    private ChambreEtat Status;

    private List<Facility> facilities;
    private List<Feature> features;

}
