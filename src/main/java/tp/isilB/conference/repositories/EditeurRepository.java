package tp.isilB.conference.repositories;
import org.springframework.data.repository.CrudRepository;
import tp.isilB.conference.entities.Auteur;
import tp.isilB.conference.entities.Editeur;

import java.util.List;

public interface EditeurRepository extends  CrudRepository<Editeur, Long> {
    Editeur findByNom(String nom);
    Auteur findByPrenom(String prenom);
    List<Auteur> findByInfos(String infos);
    Auteur findByNomAndPrenom(String nom, String prenom);
}
