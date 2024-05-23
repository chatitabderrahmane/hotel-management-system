package ma.abderrahmanechatit.gestionhotel_backend.services;

import ma.abderrahmanechatit.gestionhotel_backend.dao.Reservationrequest;
import ma.abderrahmanechatit.gestionhotel_backend.dao.Reservationresponse;
import ma.abderrahmanechatit.gestionhotel_backend.dao.UserProfileResponse;
import ma.abderrahmanechatit.gestionhotel_backend.entities.Chambre;
import ma.abderrahmanechatit.gestionhotel_backend.entities.Reservation;
import ma.abderrahmanechatit.gestionhotel_backend.entities.User;
import ma.abderrahmanechatit.gestionhotel_backend.repositories.ChambreRepository;
import ma.abderrahmanechatit.gestionhotel_backend.repositories.ReservationRepository;
import ma.abderrahmanechatit.gestionhotel_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChambreRepository chambreRepository; // Assuming you also have a Chambre repository

    public Reservationresponse createReservation(Reservationrequest request ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Optional<Chambre> chambreOpt = chambreRepository.findById(request.getChambreId());
        if (!chambreOpt.isPresent()) {
            throw new RuntimeException("Chambre not found!");
        }

        Chambre chambre = chambreOpt.get();
        Reservation reservation = new Reservation();
        reservation.setDateDebut(request.getDateDebut());
        reservation.setDateFin(request.getDateFin());
        reservation.setUser(user);
        reservation.setChambre(chambre);
                reservationRepository.save(reservation);
        return Reservationresponse.builder()
                .message("Reservation created with success"
               ).build();
    }
    public List<Reservation> getReservationsByAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        List<Reservation> reservations = reservationRepository.findByUserId(user.getId());
        return reservations;
    }

    public ResponseEntity<UserProfileResponse> profile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        UserProfileResponse response = new UserProfileResponse();
        response.setFirstname(user.getFirstname());
        response.setLastname(user.getLastname());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setAddress(user.getAdresse());
        response.setProfileImage(Base64.getEncoder().encodeToString(user.getProfileImage()));

        return ResponseEntity.ok(response);
    }
/*    public User profile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            throw new RuntimeException("User is not authenticated");
        }

        User user = (User) authentication.getPrincipal();
        return userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found"));
    }*/
 }
