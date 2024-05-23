package ma.abderrahmanechatit.gestionhotel_backend.repositories;


import ma.abderrahmanechatit.gestionhotel_backend.entities.Client;
import ma.abderrahmanechatit.gestionhotel_backend.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}