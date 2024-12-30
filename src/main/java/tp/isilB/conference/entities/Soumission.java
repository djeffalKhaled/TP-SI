package tp.isilB.conference.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

@Entity
@NoArgsConstructor
@Getter @Setter
@ToString(exclude = {"auteur", "editeur"})
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
    //@ManyToMany
    //private Collection<Conference> conferences;
    @ManyToOne
    private Editeur editeur;

    public Soumission(String nom, String description, Auteur auteur) {
        this.nom = nom; this.description = description; this.auteur = auteur; this.editeur = editeur;
    }



}
