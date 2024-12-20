package tp.isilB.conference.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString

public class Auteur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private String prenom;
    @Column(name = "infos", nullable = false, length = 256)
    private String infos;

    public Auteur(String nom, String prenom, String infos) {
        this.nom = nom; this.prenom = prenom; this.infos = infos;
    }
    public Auteur() {}

}
