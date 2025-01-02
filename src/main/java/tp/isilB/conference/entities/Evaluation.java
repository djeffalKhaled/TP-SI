package tp.isilB.conference.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int note;
    private String commentaire;
    private String etat;
    // many evaluation ont une seul soumission
    @ManyToOne
    private Soumission soumission;
    @ManyToOne
    private Evaluateur evaluateur;
    public Evaluation(int note, String commentaire, String etat, Soumission soumission, Evaluateur evaluateur) {
        this.note = note;
        this.commentaire = commentaire;
        this.etat = etat;
        this.soumission = soumission;
        this.evaluateur = evaluateur;
    }
}