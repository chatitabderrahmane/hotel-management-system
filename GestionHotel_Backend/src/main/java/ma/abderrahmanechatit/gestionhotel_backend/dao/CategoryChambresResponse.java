package ma.abderrahmanechatit.gestionhotel_backend.dao;

import lombok.Data;
import ma.abderrahmanechatit.gestionhotel_backend.entities.CategoryChambre;

import java.util.List;


public class CategoryChambresResponse {
    private String jwtToken;
    private List<CategoryChambre> categoryChambres;

    public CategoryChambresResponse(String jwtToken, List<CategoryChambre> categoryChambres) {
        this.jwtToken = jwtToken;
        this.categoryChambres = categoryChambres;
    }

    // Getters and setters
}