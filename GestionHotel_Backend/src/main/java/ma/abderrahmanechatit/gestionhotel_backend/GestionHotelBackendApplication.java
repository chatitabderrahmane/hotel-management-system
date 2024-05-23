package ma.abderrahmanechatit.gestionhotel_backend;

import ma.abderrahmanechatit.gestionhotel_backend.entities.*;
import ma.abderrahmanechatit.gestionhotel_backend.enums.ChambreEtat;
import ma.abderrahmanechatit.gestionhotel_backend.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Arrays;


@SpringBootApplication
@EnableAsync
public class GestionHotelBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionHotelBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(FeatureRepository featureRepository ,FacilityRepository facilityRepository, CategoryChambreRepository categoryChambreRepository , ChambreRepository chambreRepository, ClientRepository clientRepository, ReservationRepository reservationRepository) {
        return args -> {
            // Create Features
            Feature wifi = new Feature(null, "WiFi");
            Feature airConditioning = new Feature(null, "Air Conditioning");
            featureRepository.saveAll(Arrays.asList(wifi, airConditioning));

            // Create Facilities
            Facility swimmingPool = new Facility(null, "Swimming Pool");
            Facility gym = new Facility(null, "Gym");
            facilityRepository.saveAll(Arrays.asList(swimmingPool, gym));



            CategoryChambre categoryChambre1 = new CategoryChambre();
            categoryChambre1.setName("Single");
            categoryChambreRepository.save(categoryChambre1);

            CategoryChambre categoryChambre2 = new CategoryChambre();
            categoryChambre2.setName("Double");
            categoryChambreRepository.save(categoryChambre2);

            CategoryChambre categoryChambre3 = new CategoryChambre();
            categoryChambre3.setName("Suite");
            categoryChambreRepository.save(categoryChambre3);

            Client c1 = new Client();
            c1.setEmail("a.chatit@gmail.com");
            c1.setNom("chatit");
            c1.setPrenom("abderrahmane"); // Setting the first name
            c1.setTelephone(123456789); // Setting the telephone number
            c1.setUsername("abdelogin"); // Setting the username
            c1.setPassword("P@ssw0rd");
            clientRepository.save(c1);

            // Populate data for Chambre
            Chambre chambre1 = new Chambre();
            chambre1.setNumero("101");
            chambre1.setPrice(100.0);
            chambre1.setFeatures(Arrays.asList(wifi));
            chambre1.setFacilities(Arrays.asList(swimmingPool));
            chambre1.setStatus(ChambreEtat.Libre);
            chambre1.setType(categoryChambre1);
            chambre1.setImageUrl("https://img.freepik.com/free-photo/interior-modern-comfortable-hotel-room_1232-1822.jpg?t=st=1714412451~exp=1714416051~hmac=a19c12fdd4ab65cbf8e114fc6af1f9441a9dbeca23a385828cfa575bf3474f5c&w=740");
            chambre1.setTitle("Room 101");
            chambre1.setDescription("This is a single room.");
            chambreRepository.save(chambre1);

            Chambre chambre2 = new Chambre();
            chambre2.setNumero("102");
            chambre2.setFeatures(Arrays.asList(wifi, airConditioning));
            chambre2.setFacilities(Arrays.asList(swimmingPool));
            chambre2.setPrice(200.0);
            chambre2.setStatus(ChambreEtat.Occupée);
            chambre2.setType(categoryChambre2);
            chambre2.setImageUrl("https://img.freepik.com/free-photo/interior-modern-comfortable-hotel-room_1232-1822.jpg?t=st=1714412451~exp=1714416051~hmac=a19c12fdd4ab65cbf8e114fc6af1f9441a9dbeca23a385828cfa575bf3474f5c&w=740\n");
            chambre2.setTitle("Room 102");
            chambre2.setDescription("This is a double room.");
            chambreRepository.save(chambre2);

            Chambre chambre3 = new Chambre();
            chambre3.setNumero("103");
            chambre3.setPrice(150.0);
            chambre3.setFacilities(Arrays.asList(gym));
            chambre3.setStatus(ChambreEtat.Libre);
            chambre3.setType(categoryChambre1);
            chambre3.setImageUrl("https://img.freepik.com/free-photo/white-bedroom-hotel_1150-12633.jpg?t=st=1714412392~exp=1714415992~hmac=301ed4116fdb3417f376d98be92ade4017473514a445befc9138281286c43cdd&w=740");
            chambre3.setTitle("Room 103");
            chambre3.setDescription("This is another single room.");
            chambreRepository.save(chambre3);
            Chambre chambre4 = new Chambre();
            chambre4.setNumero("101");
            chambre4.setPrice(100.0);
            chambre4.setStatus(ChambreEtat.Libre);
            chambre4.setType(categoryChambre3);
            chambre4.setFeatures(Arrays.asList(wifi, airConditioning));
            chambre4.setImageUrl("https://img.freepik.com/photos-premium/suite-chambre-luxe-magnifique-3d-dans-hotel-televiseur-table-travail_839035-323868.jpg");
            chambre4.setTitle("Room 101");
            chambre4.setDescription("This is a single room.");
            chambreRepository.save(chambre4);
        };
    }


}
