
package ma.abderrahmanechatit.gestionhotel_backend.controllers;

 import io.swagger.v3.oas.annotations.tags.Tag;
 import jakarta.servlet.http.HttpServletResponse;
 import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
 import ma.abderrahmanechatit.gestionhotel_backend.dao.AuthenticationRequest;
 import ma.abderrahmanechatit.gestionhotel_backend.dao.AuthenticationResponse;
 import ma.abderrahmanechatit.gestionhotel_backend.dao.RegistrationRequest;
 import ma.abderrahmanechatit.gestionhotel_backend.entities.User;
 import ma.abderrahmanechatit.gestionhotel_backend.services.AuthenticationService;
 import org.apache.logging.log4j.LogManager;
 import org.apache.logging.log4j.Logger;
 import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
 import org.springframework.security.core.Authentication;
 import org.springframework.security.core.context.SecurityContextHolder;
 import org.springframework.web.bind.annotation.*;
 import org.springframework.web.multipart.MultipartFile;


 import java.io.IOException;
 import java.security.SignatureException;

@RestController
 @RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Authentication")
@CrossOrigin(origins = "http://localhost:4200")

 public class AuthController {
    private static final Logger logger = LogManager.getLogger(AuthController.class);

    private final AuthenticationService service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @RequestPart("user") @Valid RegistrationRequest request,
            @RequestPart("image") MultipartFile image
    ) {
        try {
            return ResponseEntity.ok(service.register(request, image));
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error saving user: " + e.getMessage());
        }
    }
    @GetMapping("/Profile")
    public ResponseEntity<?> getprofile() {
        try {
            User user = service.profile();
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("User is not authenticated");
        }
    }




            @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {

       return ResponseEntity.ok(service.authenticate(request));
    }
/*      @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request, HttpServletResponse response) {
        try {
            AuthenticationResponse authResponse = service.authenticate(request);
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            // General error handling
            logger.error("Authentication failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Authentication failed."+ e.getMessage());
        }
    }
*/


}

