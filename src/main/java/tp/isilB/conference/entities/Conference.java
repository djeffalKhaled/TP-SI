package tp.isilB.conference.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Date;

@Entity
@NoArgsConstructor
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
    //@ManyToMany Had major issues with postman, figure out a better relation
    //private Collection<Soumission> soumissions;

    public Conference(String titre, String theme, String etat, Date date_debut, Date date_fin, Editeur editeur) {
        this.titre = titre; this.date_debut = date_debut; this.date_fin = date_fin; this.Theme = theme; this.editeur = editeur;
    }
}
