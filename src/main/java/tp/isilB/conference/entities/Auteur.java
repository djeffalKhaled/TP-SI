package tp.isilB.conference.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Getter @Setter
@ToString(exclude = "soumissions")
public class Auteur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private String prenom;
    @Column(name = "infos", nullable = false, length = 256)
    private String infos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auteur") @JsonManagedReference
    // Un Auteur soumettre DES soumissions
    private Collection<Soumission> soumissions;

    // TODO: fix this ManyToMany, there is issues regarding initilisation in the main function, figure it out
    @ManyToMany(fetch = FetchType.EAGER)
    @Getter @Setter
    private Collection<Role> roles;

    public Auteur(String nom, String prenom, String infos) {
        this.nom = nom; this.prenom = prenom; this.infos = infos;
    }
    public Auteur() {}

}
