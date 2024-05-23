package ma.abderrahmanechatit.gestionhotel_backend.controllers;

import ma.abderrahmanechatit.gestionhotel_backend.dao.RoomsKeywordsResponse;
import ma.abderrahmanechatit.gestionhotel_backend.entities.Chambre;
import ma.abderrahmanechatit.gestionhotel_backend.enums.ChambreEtat;
import ma.abderrahmanechatit.gestionhotel_backend.repositories.ChambreRepository;
import ma.abderrahmanechatit.gestionhotel_backend.services.ChambreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chambres")
@CrossOrigin(origins = "http://localhost:4200")

public class ChambreController {

    @Autowired
    private ChambreRepository chambreRepository;
    @Autowired
    private ChambreService chambreService;

  /*  @GetMapping("/")
    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }

  */
  @GetMapping("/Filter")
  public ResponseEntity<Page<Chambre>> listRooms(
          @RequestParam(required = false) Long categoryId,
          @RequestParam(required = false) Double minPrice,
          @RequestParam(required = false) Double maxPrice,
          @RequestParam(required = false) ChambreEtat status,
          @RequestParam(required = false) List<String> facilityNames,
          @RequestParam(required = false) List<String> featureNames,
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "2") int pageSize
          ) {
      PageRequest pageRequest = PageRequest.of(page, pageSize);

      return ResponseEntity.ok(chambreService.searchRooms(categoryId, minPrice, maxPrice, status, facilityNames, featureNames, pageRequest));
  }
    @GetMapping("/")
    public Page<Chambre> getRooms(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "2") int pageSize) {
        // Pagination parameters: page index and page size
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        // Fetch rooms from the repository with pagination
        return chambreRepository.findAll(pageRequest);
    }

    @GetMapping("/latest")
    public List<Chambre> getLatestThreeChambres() {
        return chambreService.getLatestThreeChambres();
    }

    @GetMapping("/search")
    public ResponseEntity<RoomsKeywordsResponse> searchRooms(
            @RequestParam(defaultValue = "") String type,
            @RequestParam(defaultValue = "Libre") String status,
            @RequestParam(defaultValue = "0") Double minPrix,
            @RequestParam(defaultValue = "100000") Double maxPrix) {

        return ResponseEntity.ok(chambreService.searchRooms(type, status, minPrix, maxPrix));
    }




    @PostMapping("/")
    public Chambre createChambre(@RequestBody Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chambre> getChambreById(@PathVariable Long id) {
        return chambreRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chambre> updateChambre(@PathVariable Long id, @RequestBody Chambre chambreDetails) {
        return chambreRepository.findById(id)
                .map(chambre -> {
                    chambre.setNumero(chambreDetails.getNumero());
                    chambre.setPrice(chambreDetails.getPrice());
                    chambre.setTitle(chambreDetails.getTitle());
                    chambre.setDescription(chambreDetails.getDescription());
                    chambre.setImageUrl(chambreDetails.getImageUrl());
                    chambre.setType(chambreDetails.getType());
                    return ResponseEntity.ok(chambreRepository.save(chambre));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChambre(@PathVariable Long id) {
        return chambreRepository.findById(id)
                .map(chambre -> {
                    chambreRepository.delete(chambre);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
