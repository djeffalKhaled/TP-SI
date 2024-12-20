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
@ToString(exclude = "auteurs")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nomRole;

    @ManyToMany
    private Collection<Auteur> auteurs;

    public Role(String nomRole) {
        this.nomRole = nomRole;
    }


}
