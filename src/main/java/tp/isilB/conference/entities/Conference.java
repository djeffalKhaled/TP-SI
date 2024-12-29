package tp.isilB.conference.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Date;

@Entity
@Getter @Setter
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;
    private String titre ;
    private Date date_debut,date_fin ;
    private String Theme;
    private String etat;

    @ManyToOne
    private Editeur editeur;
    @ManyToMany
    private Collection<Soumission> soumessions;
}
