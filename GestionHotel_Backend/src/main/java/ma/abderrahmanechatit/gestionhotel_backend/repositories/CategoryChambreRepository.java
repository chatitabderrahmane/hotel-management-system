package ma.abderrahmanechatit.gestionhotel_backend.repositories;


import ma.abderrahmanechatit.gestionhotel_backend.entities.CategoryChambre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryChambreRepository extends JpaRepository<CategoryChambre, Long> {
     CategoryChambre findByName(String Name);
}