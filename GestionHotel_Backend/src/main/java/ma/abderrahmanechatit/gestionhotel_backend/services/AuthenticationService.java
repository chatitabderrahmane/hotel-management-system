
package ma.abderrahmanechatit.gestionhotel_backend.services;


import lombok.RequiredArgsConstructor;
import ma.abderrahmanechatit.gestionhotel_backend.dao.AuthenticationRequest;
import ma.abderrahmanechatit.gestionhotel_backend.dao.AuthenticationResponse;
import ma.abderrahmanechatit.gestionhotel_backend.dao.RegisterResponse;
import ma.abderrahmanechatit.gestionhotel_backend.dao.RegistrationRequest;
import ma.abderrahmanechatit.gestionhotel_backend.entities.Role;
 import ma.abderrahmanechatit.gestionhotel_backend.entities.User;
 import ma.abderrahmanechatit.gestionhotel_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class AuthenticationService {
   @Autowired
    private  UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private  JwtService jwtService;

    @Autowired
    private  AuthenticationManager authenticationManager;

    public User profile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            throw new RuntimeException("User is not authenticated");
        }

        User user = (User) authentication.getPrincipal();
        return userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public RegisterResponse register(RegistrationRequest request, MultipartFile file) throws IOException {
        byte[] profileImage = null;
        if (file != null && !file.isEmpty()) {
            profileImage = file.getBytes();
        }

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .role(Role.USER)
                .password(passwordEncoder.encode(request.getPassword()))
                .profileImage(profileImage) // Set the profile image
                .build();
        userRepository.save(user);

        return RegisterResponse.builder().message("Register with success").build();
    }

    private String saveFile(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get("uploads/");
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);
        return fileName;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );


        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }


}

