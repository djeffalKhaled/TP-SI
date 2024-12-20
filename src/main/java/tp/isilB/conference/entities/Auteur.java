package tp.isilB.conference.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auteur") // if deletion of auteur then delete all instances of soumission
    private Collection<Soumission> soumissions;

    // TODO: fix this ManyToMany, there is issues regarding initilisation in the main function, figure it out
    //@ManyToMany(mappedBy = "auteurs")
    //@Getter @Setter
    //private Collection<Role> roles;

    public Auteur(String nom, String prenom, String infos) {
        this.nom = nom; this.prenom = prenom; this.infos = infos;
    }
    public Auteur() {}

}
