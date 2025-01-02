package tp.isilB.conference;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tp.isilB.conference.controller.UserAppController;
import tp.isilB.conference.entities.Role;
import tp.isilB.conference.entities.UserApp;

import java.util.Arrays;
import tp.isilB.conference.repositories.RoleRepository;
import tp.isilB.conference.repositories.UserAppRepository;

@SpringBootTest
class ConferenceApplicationTests {
	@Autowired
	UserAppController userAppController;
	@Autowired
	RoleRepository roleRepo;

	@Test
	void contextLoads() {
	}

	@Test
	public void test_createUserWithUnValidRole() throws Exception {
		Role auteurRole = new Role("Auteur");
		Role editeurRole = new Role("Editeur");
		Role evaluateurRole = new Role("Evaluateur");
		roleRepo.save(auteurRole);
		roleRepo.save(editeurRole);
		roleRepo.save(evaluateurRole);


	}

}
