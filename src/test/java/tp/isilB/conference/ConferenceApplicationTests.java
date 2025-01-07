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
import tp.isilB.conference.services.UserAppService;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

@SpringBootTest
class ConferenceApplicationTests {
	@Autowired
	UserAppController userAppController;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	UserAppService userAppService;

	@Test
	void contextLoads() {
	}

	@Test
	public void userCreation() throws Exception{
		Role auteurRole = new Role("ROLE_AUTEUR"); roleRepo.save(auteurRole);
		UserApp userApp = new UserApp("email@gmail.com", "pass1234");
		userApp.setRoles(Arrays.asList(auteurRole));
		userAppService.addUserApp(userApp);
		assert userAppService.findUserAppByEmail("email@gmail.com") != null; // user added to db
	}

	@Test
	public void test_createUserWithUnValidRole() throws Exception {
		assert userAppController != null;
		assert roleRepo != null;
		Role auteurRole = new Role("ROLE_AUTEUR"); // Un auteur peut pas etre un editeur ou evaluateur
		Role editeurRole = new Role("ROLE_EDITEUR");
		Role evaluateurRole = new Role("ROLE_EVALUATEUR");
		roleRepo.save(auteurRole);
		roleRepo.save(editeurRole);
		roleRepo.save(evaluateurRole);
		UserApp userApp = new UserApp("userRole", "uservalidrole");
		userApp.setRoles(Arrays.asList(auteurRole, editeurRole, evaluateurRole));
		userAppController.createUser(userApp);
	}

	@Test
	public void test_createUserWithRoles() throws Exception {
		assert userAppController != null;
		assert roleRepo != null;
		Role auteurRole = new Role("ROLE_AUTEUR");
		Role editeurRole = new Role("ROLE_EDITEUR");
		Role evaluateurRole = new Role("ROLE_EVALUATEUR");
		roleRepo.saveAll(Arrays.asList(auteurRole, editeurRole, evaluateurRole));
		UserApp userApp = new UserApp("userRole", "uservalidrole");
		userApp.setRoles(Arrays.asList(editeurRole, auteurRole, evaluateurRole)); // un editeur peut etre un auteur ou evaluateur
		userAppController.createUser(userApp);
	}



}
