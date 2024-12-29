package tp.isilB.conference.repositories;
import org.springframework.data.repository.CrudRepository;
import tp.isilB.conference.entities.Auteur;

import java.util.List;

public interface AuteurRepository extends CrudRepository<Auteur, Long> {
    Auteur findByNom(String nom);
    Auteur findByPrenom(String prenom);
    List<Auteur> findByInfos(String infos);
    Auteur findByNomAndPrenom(String nom, String prenom);


}
