package ma.abderrahmanechatit.gestionhotel_backend.dao;

import lombok.Builder;
import lombok.Data;
import ma.abderrahmanechatit.gestionhotel_backend.entities.Chambre;
import ma.abderrahmanechatit.gestionhotel_backend.enums.ChambreEtat;
import org.aspectj.bridge.Message;

import java.util.List;

@Data
@Builder
public class RoomsKeywordsResponse {
    private List<Chambre> chambres;
    private String Message;

}
