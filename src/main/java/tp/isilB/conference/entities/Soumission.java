package tp.isilB.conference.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter @Setter
@ToString(exclude = "auteur")
public class Soumission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private String description;
    @ManyToOne
    private Auteur auteur;
    @OneToOne(cascade = CascadeType.ALL) @Getter @Setter
    private DetailsSoumission detailsSoumission;


    public Soumission(String nom, String description, Auteur auteur) {
        this.nom = nom; this.description = description; this.auteur = auteur;
    }

    public void setDetailsSoumission(DetailsSoumission details) {
        this.detailsSoumission = details;
        if (details != null) {
            details.setSoumission(this);
        }
    }
}
