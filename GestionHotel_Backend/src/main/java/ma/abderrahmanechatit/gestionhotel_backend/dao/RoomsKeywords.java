package ma.abderrahmanechatit.gestionhotel_backend.dao;

import lombok.Builder;
import lombok.Data;
import ma.abderrahmanechatit.gestionhotel_backend.entities.CategoryChambre;
import ma.abderrahmanechatit.gestionhotel_backend.enums.ChambreEtat;

@Data
@Builder
public class RoomsKeywords {
    private CategoryChambre type;
    private  double MinPrix;
    private  double MaxPrix;
    private ChambreEtat Status;
}
