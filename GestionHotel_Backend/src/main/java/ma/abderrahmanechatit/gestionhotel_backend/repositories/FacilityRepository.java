package ma.abderrahmanechatit.gestionhotel_backend.repositories;

import ma.abderrahmanechatit.gestionhotel_backend.entities.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
}