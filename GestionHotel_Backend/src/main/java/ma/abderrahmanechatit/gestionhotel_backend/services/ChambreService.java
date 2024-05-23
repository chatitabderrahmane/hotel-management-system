package ma.abderrahmanechatit.gestionhotel_backend.services;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

import ma.abderrahmanechatit.gestionhotel_backend.dao.RoomsKeywordsResponse;
import ma.abderrahmanechatit.gestionhotel_backend.entities.CategoryChambre;
import ma.abderrahmanechatit.gestionhotel_backend.entities.Chambre;
import ma.abderrahmanechatit.gestionhotel_backend.entities.Facility;
import ma.abderrahmanechatit.gestionhotel_backend.entities.Feature;
import ma.abderrahmanechatit.gestionhotel_backend.enums.ChambreEtat;
import ma.abderrahmanechatit.gestionhotel_backend.repositories.CategoryChambreRepository;
import ma.abderrahmanechatit.gestionhotel_backend.repositories.ChambreRepository;
 import org.springframework.data.domain.Pageable;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;




import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;


@Service
public class ChambreService {
    @Autowired
    private ChambreRepository chambreRepository;
    @Autowired
    private CategoryChambreRepository categoryChambreRepository;

    public Page<Chambre> searchRooms(Long categoryId, Double minPrice, Double maxPrice, ChambreEtat status, List<String> facilityNames, List<String> featureNames, Pageable pageable) {
        Specification<Chambre> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (categoryId != null) {
                predicates.add(cb.equal(root.get("type").get("id"), categoryId));
            }
            if (minPrice != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }
            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }
            if (facilityNames != null && !facilityNames.isEmpty()) {
                Join<Chambre, Facility> facilitiesJoin = root.join("facilities", JoinType.INNER);
                predicates.add(facilitiesJoin.get("name").in(facilityNames));
            }
            if (featureNames != null && !featureNames.isEmpty()) {
                Join<Chambre, Feature> featuresJoin = root.join("features", JoinType.INNER);
                predicates.add(featuresJoin.get("name").in(featureNames));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return chambreRepository.findAll(spec, pageable);
    }



    public RoomsKeywordsResponse searchRooms(String Type,
                                               String Status,
                                               double MinPrix,
                                               double MaxPrix) {

        CategoryChambre type =  categoryChambreRepository.findByName(Type);


        ChambreEtat status = ChambreEtat.valueOf(Status); // Convert String status to enum

        var result = chambreRepository.findByTypeOrStatusOrPriceBetween(type,status,MinPrix,MaxPrix);

       if(!result.isEmpty()){
           return RoomsKeywordsResponse.builder()
                   .chambres(result)
                   .Message("Success")
                   .build();
       }
       else  return RoomsKeywordsResponse.builder()
               .Message("No result found")
               .build();

     }

    public List<Chambre> getLatestThreeChambres() {
        return chambreRepository.findTop3ByOrderByIdDesc();
    }
}
