package ma.abderrahmanechatit.gestionhotel_backend.services;

import ma.abderrahmanechatit.gestionhotel_backend.entities.Feature;
import ma.abderrahmanechatit.gestionhotel_backend.except.ResourceNotFoundException;
import ma.abderrahmanechatit.gestionhotel_backend.repositories.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeatureService {

    @Autowired
    private FeatureRepository FeatureRepository;

    public List<Feature> getAllFacilities() {
        return FeatureRepository.findAll();
    }

    public Optional<Feature> getFeatureById(Long id) {
        return FeatureRepository.findById(id);
    }

    public Feature createFeature(Feature Feature) {
        return FeatureRepository.save(Feature);
    }

    public Feature updateFeature(Long id, Feature FeatureDetails) {
        Feature Feature = FeatureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feature not found with id " + id));

        Feature.setName(FeatureDetails.getName());

        return FeatureRepository.save(Feature);
    }

    public void deleteFeature(Long id) {
        Feature Feature = FeatureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feature not found with id " + id));

        FeatureRepository.delete(Feature);
    }
}
