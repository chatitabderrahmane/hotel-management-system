package ma.abderrahmanechatit.gestionhotel_backend.controllers;

import ma.abderrahmanechatit.gestionhotel_backend.entities.Facility;
import ma.abderrahmanechatit.gestionhotel_backend.except.ResourceNotFoundException;
import ma.abderrahmanechatit.gestionhotel_backend.services.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facilities")
@CrossOrigin(origins = "http://localhost:4200")

public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @GetMapping
    public List<Facility> getAllFacilities() {
        return facilityService.getAllFacilities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Facility> getFacilityById(@PathVariable Long id) {
        Facility facility = facilityService.getFacilityById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facility not found with id " + id));
        return ResponseEntity.ok(facility);
    }

    @PostMapping
    public Facility createFacility(@RequestBody Facility facility) {
        return facilityService.createFacility(facility);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Facility> updateFacility(@PathVariable Long id, @RequestBody Facility facilityDetails) {
        Facility updatedFacility = facilityService.updateFacility(id, facilityDetails);
        return ResponseEntity.ok(updatedFacility);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacility(@PathVariable Long id) {
        facilityService.deleteFacility(id);
        return ResponseEntity.noContent().build();
    }
}
