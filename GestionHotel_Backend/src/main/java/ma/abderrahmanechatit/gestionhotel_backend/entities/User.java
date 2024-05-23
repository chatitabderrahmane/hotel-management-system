    package ma.abderrahmanechatit.gestionhotel_backend.entities;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import org.springframework.data.jpa.domain.support.AuditingEntityListener;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;

    import java.security.Principal;
    import java.util.Collection;
    import java.util.List;
    import java.util.Set;
    import java.util.stream.Collectors;

    import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.enabled;

     @Entity
     @Data
    @Table(name = "users")
     @AllArgsConstructor
    @NoArgsConstructor
    @Builder
     @EntityListeners(AuditingEntityListener.class)

    public class User implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String firstname;
        private String lastname;
        private String  phone;
        private  String adresse;
        private String email;
        private  String username;
        private String password;

         @Lob
         private byte[] profileImage;

        @Enumerated(EnumType.STRING)
        private Role role;
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return  List.of( new SimpleGrantedAuthority(role.name()));

        }

         @Override
         public String getPassword() {
             return password;
         }

         @Override
         public String getUsername() {
             return email;
         }

         @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }


         public String fullName() {
             return firstname + " " + lastname ;
         }
     }
