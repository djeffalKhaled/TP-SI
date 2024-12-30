package tp.isilB.conference.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tp.isilB.conference.entities.UserApp;

public interface UserAppRepository extends JpaRepository<UserApp, Integer>{
    public UserApp findByEmail(String Email);

}
