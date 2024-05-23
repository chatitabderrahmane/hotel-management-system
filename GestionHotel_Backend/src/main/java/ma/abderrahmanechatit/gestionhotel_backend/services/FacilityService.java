package ma.abderrahmanechatit.gestionhotel_backend.services;

import ma.abderrahmanechatit.gestionhotel_backend.entities.Facility;
import ma.abderrahmanechatit.gestionhotel_backend.except.ResourceNotFoundException;
import ma.abderrahmanechatit.gestionhotel_backend.repositories.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;

    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }

    public Optional<Facility> getFacilityById(Long id) {
        return facilityRepository.findById(id);
    }

    public Facility createFacility(Facility facility) {
        return facilityRepository.save(facility);
    }

    public Facility updateFacility(Long id, Facility facilityDetails) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facility not found with id " + id));

        facility.setName(facilityDetails.getName());

        return facilityRepository.save(facility);
    }

    public void deleteFacility(Long id) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Facility not found with id " + id));

        facilityRepository.delete(facility);
    }
}
