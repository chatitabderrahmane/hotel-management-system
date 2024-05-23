package ma.abderrahmanechatit.gestionhotel_backend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import ma.abderrahmanechatit.gestionhotel_backend.dao.CategoryChambresResponse;
import ma.abderrahmanechatit.gestionhotel_backend.entities.CategoryChambre;
import ma.abderrahmanechatit.gestionhotel_backend.repositories.CategoryChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/categorychambres")
@CrossOrigin(origins = "http://localhost:4200")

public class CategoryChambreController {
    @Autowired
    private CategoryChambreRepository categoryChambreRepository;

   @GetMapping("/")
    public List<CategoryChambre> getAllCategoryChambres(HttpServletRequest request) {
         return categoryChambreRepository.findAll();
    }
     @PostMapping("/")
    public CategoryChambre createCategoryChambre(@RequestBody CategoryChambre categoryChambre) {
        return categoryChambreRepository.save(categoryChambre);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryChambre> getCategoryChambreById(@PathVariable Long id) {
        return categoryChambreRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryChambre> updateCategoryChambre(@PathVariable Long id, @RequestBody CategoryChambre categoryChambreDetails) {
        return categoryChambreRepository.findById(id)
                .map(categoryChambre -> {
                    categoryChambre.setName(categoryChambreDetails.getName());
                    return ResponseEntity.ok(categoryChambreRepository.save(categoryChambre));
                })
                .orElse(ResponseEntity.notFound().build());
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryChambre(@PathVariable Long id) {
        return categoryChambreRepository.findById(id)
                .map(categoryChambre -> {
                    categoryChambreRepository.delete(categoryChambre);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
