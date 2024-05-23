package ma.abderrahmanechatit.gestionhotel_backend.repositories;


import ma.abderrahmanechatit.gestionhotel_backend.entities.CategoryChambre;
import ma.abderrahmanechatit.gestionhotel_backend.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUserId(Long id);
}