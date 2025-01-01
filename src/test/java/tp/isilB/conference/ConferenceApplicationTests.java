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
		Role auteurRole = new Role("ROLE_AUTEUR");
		Role editeurRole = new Role("ROLE_EDITEUR");
		Role evaluateurRole = new Role("ROLE_EVALUATEUR");
		roleRepo.save(auteurRole);
		roleRepo.save(editeurRole);
		roleRepo.save(evaluateurRole);

		UserApp userApp = new UserApp("AuthorWithRolesEditeurEvaluateur", "hackthis");
		userApp.setRoles(Arrays.asList(auteurRole, editeurRole, evaluateurRole));
		userAppController.createUser(userApp);
	}

	@Test
	public void test_createUserWithRoles() throws Exception {
		Role auteurRole = new Role("ROLE_AUTEUR");
		Role editeurRole = new Role("ROLE_EDITEUR");
		Role evaluateurRole = new Role("ROLE_EVALUATEUR");
		roleRepo.save(auteurRole);
		roleRepo.save(editeurRole);
		UserApp userApp = new UserApp("userRole", "uservalidrole");
		userApp.setRoles(Arrays.asList(editeurRole, auteurRole, evaluateurRole));
		userAppController.createUser(userApp);
	}

}
