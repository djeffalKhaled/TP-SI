package tp.isilB.conference.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    @ManyToMany
    private Collection<UserApp> userApp;

    public Role(String nomRole) {
        this.nomRole = nomRole;
    }


}
