package tp.isilB.conference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tp.isilB.conference.controller.UserAppController;
import tp.isilB.conference.entities.*;
import tp.isilB.conference.repositories.*;
import tp.isilB.conference.services.UserAppService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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
	private DetailsSoumissionRepository detailsSoumRepo;
	@Autowired
	private EditeurRepository editeurRepo;
	@Autowired
	private UserAppService userAppService;
	@Autowired
	private UserAppController userAppController;

	@Override
	public void run(String... args) throws Exception {
		// Création des auteurs
		Auteur auteur1 = new Auteur("Nom1", "Prenom1", "Université des Sabotages Houari Boumedienne");
		Auteur auteur2 = new Auteur("Nom2", "Prenom2", "Djelfa Superior University of Quantum Science");
		Auteur auteur3 = new Auteur("Nom3", "Prenom3", "Ecole Superieur de Springboot");

		auteurRepo.saveAll(Arrays.asList(auteur1, auteur2, auteur3));

		// Création des soumissions
		Soumission soumission1 = new Soumission("soumission1", "Science", auteur1);
		Soumission soumission2 = new Soumission("soumission2", "Math", auteur1);
		Soumission soumission3 = new Soumission("soumission3", "Physique", auteur2);

		soumission1.setDetailsSoumission(new DetailsSoumission("1999-02-20", "2000-02-20"));
		soumission2.setDetailsSoumission(new DetailsSoumission("2004-02-20", "2003-02-20"));
		soumission3.setDetailsSoumission(new DetailsSoumission("2008-02-20", "2009-02-20"));

		soumRepo.saveAll(Arrays.asList(soumission1, soumission2, soumission3));

		// Création des éditeurs
		Editeur editeur1 = new Editeur();
		editeur1.setNom("Editeur1");
		editeur1.setPrenom("Prenom1");
		editeur1.setInfos("Spécialiste en conférences scientifiques");

		Editeur editeur2 = new Editeur();
		editeur2.setNom("Editeur2");
		editeur2.setPrenom("Prenom2");
		editeur2.setInfos("Expert en physique quantique");

		editeurRepo.saveAll(Arrays.asList(editeur1, editeur2));

		// Création des conférences
		Conference conference1 = new Conference();
		conference1.setTitre("Conférence Internationale sur l'IA");
		conference1.setDate_debut(new Date());
		conference1.setDate_fin(new Date());
		conference1.setTheme("Intelligence Artificielle");
		conference1.setEtat("Active");
		conference1.setEditeur(editeur1);
		conference1.setSoumessions(Arrays.asList(soumission1, soumission2));

		Conference conference2 = new Conference();
		conference2.setTitre("Symposium sur les mathématiques avancées");
		conference2.setDate_debut(new Date());
		conference2.setDate_fin(new Date());
		conference2.setTheme("Mathématiques");
		conference2.setEtat("Planifiée");
		conference2.setEditeur(editeur2);
		conference2.setSoumessions(Arrays.asList(soumission3));

		// Afficher les détails des auteurs, soumissions, éditeurs et conférences
		for (Auteur auteur : auteurRepo.findAll()) {
			System.out.println(auteur);
		}

		System.out.println("Les infos du Nom1: " + auteurRepo.findByNomAndPrenom("Nom1", "Prenom1").getInfos());

		for (Editeur editeur : editeurRepo.findAll()) {
			System.out.println("Éditeur: " + editeur.getNom() + ", Infos: " + editeur.getInfos());
		}

		System.out.println("Conférences:");
		System.out.println(conference1);
		System.out.println(conference2);

		// Création d'un utilisateur
		UserApp userApp = new UserApp("name", "1234");
		userAppController.createUser(userApp);
	}
}
