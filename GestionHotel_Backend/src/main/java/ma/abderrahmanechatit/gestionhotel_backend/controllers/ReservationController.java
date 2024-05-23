package ma.abderrahmanechatit.gestionhotel_backend.controllers;


import ma.abderrahmanechatit.gestionhotel_backend.dao.Reservationrequest;
import ma.abderrahmanechatit.gestionhotel_backend.dao.Reservationresponse;
import ma.abderrahmanechatit.gestionhotel_backend.dao.UserProfileResponse;
import ma.abderrahmanechatit.gestionhotel_backend.entities.Reservation;
import ma.abderrahmanechatit.gestionhotel_backend.entities.User;
import ma.abderrahmanechatit.gestionhotel_backend.repositories.ReservationRepository;
import ma.abderrahmanechatit.gestionhotel_backend.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "http://localhost:4200")

public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationService reservationService;
    @GetMapping("/")
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @PostMapping("/Create")
    public ResponseEntity<Reservationresponse> createReservation(@RequestBody Reservationrequest request) {
        return ResponseEntity.ok(reservationService.createReservation(request));
    }
    @GetMapping("/My")
    public ResponseEntity<?> getMyReservations() {
         List<Reservation> reservationsOpt = reservationService.getReservationsByAuthenticatedUser();
             return ResponseEntity.ok(reservationsOpt);

    }
    @GetMapping("/Profile")
    public ResponseEntity<?> profile() {
        try {

            return ResponseEntity.ok(reservationService.profile());
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("User is not authenticated");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        return reservationRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation updatedReservation) {
        return reservationRepository.findById(id)
                .map(reservation -> {
                    reservation.setDateFin(updatedReservation.getDateFin());
                    reservation.setDateDebut(updatedReservation.getDateDebut());
                    reservation.setUser(updatedReservation.getUser());
                    reservation.setChambre(updatedReservation.getChambre());
                    return ResponseEntity.ok(reservationRepository.save(reservation));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        return reservationRepository.findById(id)
                .map(reservation -> {
                    reservationRepository.delete(reservation);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
