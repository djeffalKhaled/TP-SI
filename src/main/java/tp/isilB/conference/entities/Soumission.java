package tp.isilB.conference.entities;

import com.fasterxml.jackson.annotation.*;
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
    private String titre;
    private String resume;
    private String documentPDF;
    @ManyToOne @JsonBackReference("soumission-auteur")
    private Auteur auteur;
    @OneToOne(cascade = CascadeType.ALL) @Getter @Setter
    private DetailsSoumission detailsSoumission;
    @ManyToOne @JsonBackReference("conference-soumission") // Les soumissions sont liées à une conférence
    private Conference conference;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "soumission")
    private Evaluation evaluation;
    public Soumission(String titre, String resume, String documentPDF, Auteur auteur, Conference conference) {
        this.titre = titre; this.resume = resume; this.documentPDF =  documentPDF; this.auteur = auteur; this.conference = conference;
    }

    public void setDetailsSoumission(DetailsSoumission details) {
        this.detailsSoumission = details;
        if (details != null) {
            details.setSoumission(this);
        }
    }
}
