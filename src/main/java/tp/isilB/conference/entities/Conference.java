package tp.isilB.conference.entities;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.Date;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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


    @ManyToOne @JsonIgnore // Une conférence est créée par un éditeur
    private Editeur editeur;

    // Added a JsonIgnore due to issues with POST
    // Une conférence peut contenir plusieurs soumissions
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conference") @JsonManagedReference("conference-soumission") @Getter @Setter
    private Collection<Soumission> soumissions;

    public Conference(String titre, String theme, String etat, Date date_debut, Date date_fin, Editeur editeur) {
        this.titre = titre; this.Theme = theme; this.etat = etat; this.date_debut = date_debut; this.date_fin = date_fin;  this.editeur = editeur;
    }
}
