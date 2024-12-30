package tp.isilB.conference.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NoArgsConstructor
@Getter @Setter
@ToString(exclude = {"auteur", "editeur"})
public class Soumission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private String description;
    @ManyToOne @JsonBackReference
    private Auteur auteur;
    @OneToOne(cascade = CascadeType.ALL) @Getter @Setter
    private DetailsSoumission detailsSoumission;
    @ManyToOne @JsonBackReference // Les soumissions sont liées à une conférence
    private Conference conference;
    @ManyToOne
    private Editeur editeur;

    public Soumission(String nom, String description, Auteur auteur, Conference conference) {
        this.nom = nom; this.description = description; this.auteur = auteur; this.editeur = editeur; this.conference = conference;
    }



}
