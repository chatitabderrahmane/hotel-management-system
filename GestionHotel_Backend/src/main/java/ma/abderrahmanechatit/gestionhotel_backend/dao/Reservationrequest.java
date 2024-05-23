package ma.abderrahmanechatit.gestionhotel_backend.dao;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Builder
@Data
public class Reservationrequest {
    private  Long chambreId;
    private  Date dateDebut;
    private Date dateFin;


}
