package tp.isilB.conference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tp.isilB.conference.entities.Auteur;
import tp.isilB.conference.entities.DetailsSoumission;
import tp.isilB.conference.entities.Role;
import tp.isilB.conference.entities.Soumission;
import tp.isilB.conference.repositories.AuteurRepository;
import lombok.*;
import tp.isilB.conference.repositories.DetailsSoumissionRepository;
import tp.isilB.conference.repositories.SoumissionRepository;


import java.util.ArrayList;
import java.util.Collection;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

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

	@Override
	public void run(String... args) throws Exception {
		Auteur auteur1 = new Auteur("Nom1", "Prenom1", "Université des Sabotages Houari Boumedienne");
		Auteur auteur2 = new Auteur("Nom2", "Prenom2", "Djelfa Superior University of Quantum Science");
		Auteur auteur3 = new Auteur("Nom3", "Prenom3", "Ecole Superieur de Springboot");

		auteurRepo.save(auteur1);
		auteurRepo.save(auteur2);
		auteurRepo.save(auteur3);

		Soumission soumission1 = new Soumission("soumission1", "Science", auteur1);
		Soumission soumission2 = new Soumission("soumission1", "Math", auteur1);
		Soumission soumission3 = new Soumission("soumission1", "Physique", auteur2);

		soumission1.setDetailsSoumission(new DetailsSoumission("1999-02-20", "2000-02-20"));
		soumission2.setDetailsSoumission(new DetailsSoumission("2004-02-20", "2003-02-20"));
		soumission3.setDetailsSoumission(new DetailsSoumission("2008-02-20", "2009-02-20"));
		soumRepo.save(soumission1);
		soumRepo.save(soumission2);
		soumRepo.save(soumission3);

		for (Auteur auteur : auteurRepo.findAll()) {
			System.out.println(auteur.toString());
		}
		System.out.println("Les infos du nom1: "+ auteurRepo.findByNomAndPrenom("Nom1", "Prenom1").getInfos());
	}

}
