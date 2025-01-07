package tp.isilB.conference.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@ToString()
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nomRole;

    @ManyToMany @JsonBackReference("user-role")
    private Collection<UserApp> userApp;

    public Role(String nomRole) {
        this.nomRole = nomRole;
    }


}
