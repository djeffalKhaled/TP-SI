package tp.isilB.conference.repositories;

import org.springframework.data.repository.CrudRepository;
import tp.isilB.conference.entities.Auteur;
import tp.isilB.conference.entities.Soumission;

public interface SoumissionRepository extends CrudRepository<Soumission, Long> {
    public Soumission findByNom(String nom);
    public Soumission findByAuteur (Auteur auteur);
    public Soumission findAllSoumissions ( );
}
