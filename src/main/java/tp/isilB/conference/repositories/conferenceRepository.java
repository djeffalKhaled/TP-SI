package tp.isilB.conference.repositories;
import org.springframework.data.repository.CrudRepository;
import tp.isilB.conference.entities.Conference;


public interface conferenceRepository extends CrudRepository<Conference, Long> {

}
