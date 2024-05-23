package ma.abderrahmanechatit.gestionhotel_backend.dao;

import lombok.*;

@Getter
@Setter
@Builder
public class AuthenticationResponse {
    private String token;
}