package tp.isilB.conference.entities;

import jakarta.persistence.*;
import lombok.*;
import tp.isilB.conference.validators.RolesValid;

import java.util.Collection;
import java.util.List;
import java.util.Set;
// Partie Gestion des Utilisteurs

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter @Setter
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String password; // TODO: find a way to encrypt this for security purposes

    @ManyToMany(fetch = FetchType.EAGER)
    @RolesValid @Getter @Setter // Le premier rôle est le rôle principal
    private Collection<Role> roles;

    public UserApp(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
