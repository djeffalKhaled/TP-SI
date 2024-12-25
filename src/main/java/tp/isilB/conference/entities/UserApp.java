package tp.isilB.conference.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter @Setter
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String email;
    private String password; // TODO: find a way to encrypt this for security purposes

    public UserApp(String email, String password) {
        this.email = email; this.password = password;
    }


}
