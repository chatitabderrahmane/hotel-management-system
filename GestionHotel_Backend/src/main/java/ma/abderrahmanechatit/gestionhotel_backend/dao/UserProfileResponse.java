package ma.abderrahmanechatit.gestionhotel_backend.dao;


import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Data;

@Data

public class UserProfileResponse {
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String address;

    private String profileImage; // This should be a base64 encoded string
}