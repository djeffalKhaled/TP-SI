package tp.isilB.conference.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tp.isilB.conference.validators.RolesValid;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
// Partie Gestion des Utilisteurs

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"auteur", "editeur", "evaluateur"})
@Getter @Setter
public class UserApp implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @RolesValid @Getter @Setter @JsonManagedReference("user-role")// Le premier rôle est le rôle principal
    private Collection<Role> roles;

    @OneToOne @JsonManagedReference("user-auteur")
    private Auteur auteur;
    @OneToOne @JsonManagedReference("user-editeur")
    private Editeur editeur;
    @OneToOne @JsonManagedReference("user-evaluateur")
    private Evaluateur evaluateur;

    public UserApp(String email, String password) {
        this.email = email;
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNomRole()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }
}
