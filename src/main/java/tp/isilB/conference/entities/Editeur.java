package tp.isilB.conference.entities;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Collection;

@Entity
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "editeur")
    private Collection<Soumission> conferences;
}
