package ma.abderrahmanechatit.gestionhotel_backend.controllers;

import ma.abderrahmanechatit.gestionhotel_backend.entities.Feature;
import ma.abderrahmanechatit.gestionhotel_backend.except.ResourceNotFoundException;
import ma.abderrahmanechatit.gestionhotel_backend.services.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/features")
@CrossOrigin(origins = "http://localhost:4200")

public class FeatureController {

    @Autowired
    private FeatureService FeatureService;

    @GetMapping
    public List<Feature> getAllFacilities() {
        return FeatureService.getAllFacilities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feature> getFeatureById(@PathVariable Long id) {
        Feature Feature = FeatureService.getFeatureById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feature not found with id " + id));
        return ResponseEntity.ok(Feature);
    }

    @PostMapping
    public Feature createFeature(@RequestBody Feature Feature) {
        return FeatureService.createFeature(Feature);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feature> updateFeature(@PathVariable Long id, @RequestBody Feature FeatureDetails) {
        Feature updatedFeature = FeatureService.updateFeature(id, FeatureDetails);
        return ResponseEntity.ok(updatedFeature);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeature(@PathVariable Long id) {
        FeatureService.deleteFeature(id);
        return ResponseEntity.noContent().build();
    }
}
