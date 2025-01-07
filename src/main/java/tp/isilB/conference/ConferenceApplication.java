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
	private EditeurRepository editeurRepo;
	@Autowired
	private EvaluateurRepository evaluateurRepo;
	@Autowired
	private SoumissionRepository soumRepo;
	@Autowired
	private EvaluationRepository evaluationRepo;
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
		Role adminRole = new Role("ROLE_ADMIN");
		roleRepo.saveAll(Arrays.asList(auteurRole, editeurRole, evaluateurRole, adminRole));

		// Création des Auteurs
		Auteur auteur1 = new Auteur("Mohammed", "Smith", "Diplomé de l'université des Science et Technologie Houari Boumedienne");
		Auteur auteur2 = new Auteur("James", "Newton", "Ingénieur chez Springboot");
		Auteur auteur3 = new Auteur("Albert", "Turing", "Expert en Mathématiques"); // Albert est un evaluateur + auteur
		Auteur auteur4 = new Auteur("Martin", "Pierre", "Expert en Mathématiques"); // Martin est un editeur + evaluateur + auteur
		auteurRepo.saveAll(Arrays.asList(auteur1, auteur2, auteur3, auteur4));


		// Création des éditeurs
		Editeur editeur1 = new Editeur("Jhon", "Marston", "Spécialiste en conférences scientifiques");
		Editeur editeur2 = new Editeur("Martin", "Pierre", "Expert en Mathématiques");
		Editeur editeur3 = new Editeur("Achrouf", "Islam", "Expert en Informatique Quantique");
		editeurRepo.saveAll(Arrays.asList(editeur1, editeur2, editeur3));


		// Creation des Evaluateur
		Evaluateur evaluateur1 = new Evaluateur("Dupont", "Jean", "Expert en Sciences");
		Evaluateur evaluateur2 = new Evaluateur("Albert", "Turing", "Expert en Mathématiques");
		Evaluateur evaluateur3 = new Evaluateur("Martin","Pierre", "Expert en Mathématiques");
		evaluateurRepo.saveAll(Arrays.asList(evaluateur1, evaluateur2, evaluateur3));


		// Création des conférences
		Conference conference1 = new Conference("Conférence Internationale sur l'IA", "Intelligence Artificielle", "Active", new Date(), new Date(), editeur1);
		Conference conference2 = new Conference("Symposium sur les mathématiques avancées", "Mathématiques", "Planifiée", new Date(), new Date(), editeur2);
		Conference conference3 = new Conference("Le future et L'Informatique Quantique", "Calcul Haut Performance", "Faite", new Date(), new Date(), editeur3);
		confRepo.saveAll(Arrays.asList(conference1, conference2, conference3));


		// Création des Soumissions
		Soumission soumission1 = new Soumission("L'Effet de L'IA", "Le future et l'IA", "PDF1", auteur1, conference1);
		Soumission soumission2 = new Soumission("Etude sur Mathématiques", "Etude de PHD sur des chose math", "PDF2", auteur1, conference2);
		Soumission soumission3 = new Soumission("Le future et le CPU", "La création de nouveau processeur d'intel avec puissance de 5000kghz 100 coueur","PDF3", auteur3, conference3);
		conference1.setSoumissions(Arrays.asList(soumission1));
		conference2.setSoumissions(Arrays.asList(soumission2));
		conference3.setSoumissions(Arrays.asList(soumission3));

		soumission1.setDetailsSoumission(new DetailsSoumission("1999-02-20", "2000-02-20"));
		soumission2.setDetailsSoumission(new DetailsSoumission("2004-02-20", "2003-02-20"));
		soumission3.setDetailsSoumission(new DetailsSoumission("2008-02-20", "2009-02-20"));
		soumRepo.saveAll(Arrays.asList(soumission1, soumission2, soumission3));


		// Creation des Evaluations
		Evaluation evaluation2 = new Evaluation(5, "Soumission à améliorer", "EN ATTENTE", soumission1, evaluateur2);
		Evaluation evaluation1 = new Evaluation(10, "Très bonne soumission", "ACCEPTEE", soumission3, evaluateur1);
		Evaluation evaluation3 = new Evaluation(3, "Soumission Stupide!!", "REJECTER", soumission2, evaluateur1);
		evaluationRepo.saveAll(Arrays.asList(evaluation1, evaluation2, evaluation3));


		// Creation des utilisateurs
		UserApp userApp = new UserApp("tout@gmail.com", "tout"); // tout les roles editeur/auteur/evaluateur
		userApp.setEditeur(editeur2);  userApp.setAuteur(auteur4); userApp.setEvaluateur(evaluateur3);
		userApp.setRoles(Arrays.asList(editeurRole, auteurRole, evaluateurRole));
		userAppController.createUser(userApp);

		userApp = new UserApp("editeur@gmail.com", "editeur");
		userApp.setRoles(Arrays.asList(editeurRole)); userApp.setEditeur(editeur1);
		userAppController.createUser(userApp);

		userApp = new UserApp("auteur@gmail.com", "auteur");
		userApp.setRoles(Arrays.asList(auteurRole)); userApp.setAuteur(auteur1);
		userAppController.createUser(userApp);

		userApp = new UserApp("evaluateur@gmail.com", "evaluateur");
		userApp.setRoles(Arrays.asList(evaluateurRole, auteurRole)); userApp.setEvaluateur(evaluateur2); userApp.setAuteur(auteur2);
		userAppController.createUser(userApp);

		userApp = new UserApp("admin", "admin");
		userApp.setRoles(Arrays.asList(adminRole));
		userAppController.createUser(userApp);



	}


}
