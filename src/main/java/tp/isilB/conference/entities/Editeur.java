package tp.isilB.conference.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Collection;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter @Setter
@ToString(exclude = "conferences")
public class Editeur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private String prenom;
    @Column(name = "infos", nullable = false, length = 256)
    private String infos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "editeur") @JsonIgnore
    private Collection<Conference> conferences;
    @OneToOne(mappedBy = "editeur") @JsonBackReference("user-editeur")
    private UserApp user;

    public Editeur(String nom, String prenom, String infos) {
        this.nom = nom; this.prenom = prenom; this.infos = infos;
    }
}
