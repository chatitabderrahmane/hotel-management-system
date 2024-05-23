package ma.abderrahmanechatit.gestionhotel_backend.repositories;

import ma.abderrahmanechatit.gestionhotel_backend.entities.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature,Long> {
}
