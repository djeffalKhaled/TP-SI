package tp.isilB.conference.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Getter
@Setter
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Min(value = 0, message = "Note d'Evaluation doit etre >= 0")
    @Max(value = 10, message = "Note d'Evaluation doit etre <= 10")
    private int note;
    private String commentaire;
    private String etat;
    @OneToOne @JsonProperty("soumission")
    private Soumission soumission;
    @ManyToOne @JsonBackReference("evaluateur-evaluation")
    private Evaluateur evaluateur;
    public Evaluation(int note, String commentaire, String etat, Soumission soumission, Evaluateur evaluateur) {
        this.note = note;
        this.commentaire = commentaire;
        this.etat = etat;
        this.soumission = soumission;
        this.evaluateur = evaluateur;
    }
}