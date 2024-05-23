package ma.abderrahmanechatit.gestionhotel_backend.repositories;

import io.micrometer.common.lang.Nullable;
import ma.abderrahmanechatit.gestionhotel_backend.entities.CategoryChambre;
import ma.abderrahmanechatit.gestionhotel_backend.entities.Chambre;
import ma.abderrahmanechatit.gestionhotel_backend.enums.ChambreEtat;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChambreRepository extends JpaRepository<Chambre, Long> , JpaSpecificationExecutor<Chambre> {
    List<Chambre> findByTypeOrStatusOrPriceBetween(
            CategoryChambre type,
            ChambreEtat status,
            double minPrice,
            double maxPrice);


    @Query(value = "SELECT c FROM Chambre c ORDER BY c.id DESC")
    List<Chambre> findTop3ByOrderByIdDesc();


}
