package tp.isilB.conference.repositories;

import org.springframework.data.repository.CrudRepository;
import tp.isilB.conference.entities.DetailsSoumission;

public interface DetailsSoumissionRepository extends CrudRepository<DetailsSoumission, Long> {
    public DetailsSoumission findById(int id);
    public DetailsSoumission findByDateDeSoumission(String dateDeSoumission);
    public DetailsSoumission findByDateDeModification(String dateDeModification);
}
