package tp.isilB.conference.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter @Setter
@ToString(exclude = "soumission")
public class DetailsSoumission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String dateDeSoumission;
    private String dateDeModification;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "detailsSoumission")
    private Soumission soumission;

    public DetailsSoumission(String dateDeSoumission, String dateDeModification) {
        this.dateDeSoumission = dateDeSoumission; this.dateDeModification = dateDeModification;
    }

}
