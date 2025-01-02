package tp.isilB.conference.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Evaluateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private String prenom;
    @Column(name ="infos" , nullable = false ,length = 256)
    private String infos;
    @OneToMany(mappedBy = "evaluateur", cascade = CascadeType.ALL)  @JsonManagedReference("evaluateur-evaluation")
    private List<Evaluation> evaluations;
    public Evaluateur(String nom, String prenom, String infos) {
        this.nom = nom;
        this.prenom = prenom;
        this.infos = infos;
    }
}