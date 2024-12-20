package tp.isilB.conference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tp.isilB.conference.entities.Auteur;
import tp.isilB.conference.repositories.AuteurRepository;
import lombok.*;

@SpringBootApplication
public class ConferenceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConferenceApplication.class, args);
	}

	@Autowired
	private AuteurRepository auteurRepo;

	@Override
	public void run(String... args) throws Exception {
		auteurRepo.save(new Auteur("Nom1", "Prenom1", "Universite1"));
		auteurRepo.save(new Auteur("Nom2", "Prenom2", "Universite2"));
		auteurRepo.save(new Auteur("Nom3", "Prenom3", "Universite3"));

		for (Auteur auteur : auteurRepo.findAll()) {
			System.out.println(auteur.toString());
		}
	}

}
