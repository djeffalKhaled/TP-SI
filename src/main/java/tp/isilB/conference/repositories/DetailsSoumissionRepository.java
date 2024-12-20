package tp.isilB.conference.repositories;

import org.springframework.data.repository.CrudRepository;
import tp.isilB.conference.entities.DetailsSoumission;

public interface DetailsSoumissionRepository extends CrudRepository<DetailsSoumission, Long> {
    public DetailsSoumission findById(int id);

}
