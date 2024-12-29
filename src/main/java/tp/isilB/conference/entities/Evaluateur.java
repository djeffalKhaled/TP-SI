package tp.isilB.conference.entities;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter @Setter
public class Evaluateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private String prenom;


}
