package tp.isilB.conference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;
import tp.isilB.conference.controller.UserAppController;
import tp.isilB.conference.entities.*;
import tp.isilB.conference.repositories.*;
import tp.isilB.conference.services.UserAppService;


import java.util.*;

@SpringBootApplication
public class ConferenceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConferenceApplication.class, args);
	}

	@Autowired
	private AuteurRepository auteurRepo;
	@Autowired
	private SoumissionRepository soumRepo;
	@Autowired
	private EditeurRepository editeurRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private ConferenceRepository confRepo;
	@Autowired
	private UserAppService userAppService;
	@Autowired
	private UserAppController userAppController;
	@Autowired
	private DetailsSoumissionRepository detailsSoumRepo;

	@Override
	public void run(String... args) throws Exception {
		// Création des roles
		Role auteurRole = new Role("ROLE_AUTEUR");
		Role editeurRole = new Role("ROLE_EDITEUR");
		Role evaluateurRole = new Role("ROLE_EVALUATEUR");
		roleRepo.save(auteurRole);
		roleRepo.save(editeurRole);
		roleRepo.save(evaluateurRole);

		// Création des Auteurs
		Auteur auteur1 = new Auteur("Nom1", "Prenom1", "Université des Sabotages Houari Boumedienne");
		Auteur auteur2 = new Auteur("Nom2", "Prenom2", "Djelfa Superior University of Quantum Science");
		Auteur auteur3 = new Auteur("Nom3", "Prenom3", "Ecole Superieur de Springboot");

		auteurRepo.saveAll(Arrays.asList(auteur1, auteur2, auteur3));

		// Création des éditeurs
		Editeur editeur1 = new Editeur("Editeur1", "Prenom1", "Spécialiste en conférences scientifiques");
		Editeur editeur2 = new Editeur("Editeur2", "Prenom2", "Expert en Physique Quantique");
		Editeur editeur3 = new Editeur("Achrouf", "Islam", "Expert en Informatique Quantique");

		editeurRepo.saveAll(Arrays.asList(editeur1, editeur2, editeur3));

		// Création des conférences
		Conference conference1 = new Conference("Conférence Internationale sur l'IA", "Intelligence Artificielle",
				"Active", new Date(), new Date(), editeur1);

		Conference conference2 = new Conference("Symposium sur les mathématiques avancées", "Mathématiques", "Planifiée", new Date(), new Date(), editeur3);
		confRepo.saveAll(Arrays.asList(conference1, conference2));

		// Création des Soumissions
		Soumission soumission1 = new Soumission("soumission1", "Science", auteur1, conference1);
		Soumission soumission2 = new Soumission("soumission1", "Math", auteur1, conference2);
		Soumission soumission3 = new Soumission("soumission1", "Physique", auteur2, conference2);
		conference1.setSoumissions(Arrays.asList(soumission1));
		conference2.setSoumissions(Arrays.asList(soumission2, soumission3));

		soumission1.setDetailsSoumission(new DetailsSoumission("1999-02-20", "2000-02-20"));
		soumission2.setDetailsSoumission(new DetailsSoumission("2004-02-20", "2003-02-20"));
		soumission3.setDetailsSoumission(new DetailsSoumission("2008-02-20", "2009-02-20"));
		soumRepo.saveAll(Arrays.asList(soumission1, soumission2, soumission3));

		for (Auteur auteur : auteurRepo.findAll()) {
			System.out.println(auteur.toString());
		}
		System.out.println("Les infos du nom1: "+ auteurRepo.findByNomAndPrenom("Nom1", "Prenom1").getInfos());

		UserApp userApp = new UserApp("allroler", "password1233");
		userApp.setRoles(Arrays.asList(editeurRole, auteurRole, evaluateurRole));
		userAppController.createUser(userApp);


		userApp = new UserApp("justEditor", "thisispassword");
		userApp.setRoles(Arrays.asList(editeurRole));
		userAppController.createUser(userApp);

		userApp = new UserApp("pureAuthorEditor", "BooterSpring");
		userApp.setRoles(Arrays.asList(auteurRole));
		userAppController.createUser(userApp);

		userApp = new UserApp("justEvaluateur", "13214");
		userApp.setRoles(Arrays.asList(evaluateurRole, auteurRole));
		userAppController.createUser(userApp);



	}


}
