package tp.isilB.conference.repositories;

import org.springframework.data.repository.CrudRepository;
import tp.isilB.conference.entities.Auteur;
import tp.isilB.conference.entities.Soumission;

public interface SoumissionRepository extends CrudRepository<Soumission, Long> {
    public Soumission findByTitre(String titre);
    public Soumission findByAuteur (Auteur auteur);
}
