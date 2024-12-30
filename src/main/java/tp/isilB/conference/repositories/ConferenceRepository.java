package tp.isilB.conference.repositories;
import org.springframework.data.repository.CrudRepository;
import tp.isilB.conference.entities.Conference;


public interface ConferenceRepository extends CrudRepository<Conference, Long> {

}
