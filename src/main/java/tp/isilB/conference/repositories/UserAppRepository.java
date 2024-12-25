package tp.isilB.conference.repositories;

import org.springframework.data.repository.CrudRepository;
import tp.isilB.conference.entities.UserApp;

public interface UserAppRepository extends CrudRepository<UserApp, Long> {
    public UserApp findByEmail(String Email);

}
