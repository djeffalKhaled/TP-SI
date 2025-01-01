package tp.isilB.conference;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tp.isilB.conference.controller.UserAppController;
import tp.isilB.conference.entities.*;
import tp.isilB.conference.repositories.*;
import tp.isilB.conference.services.SoumissionService;
import tp.isilB.conference.services.DetailsSoumissionService;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ConferenceApplicationTests {
    @Autowired
    UserAppController userAppController;
    @Autowired
    RoleRepository roleRepo;
    @Autowired
    SoumissionService soumissionService;
    @Autowired
    DetailsSoumissionService detailsSoumissionService;
    @Autowired
    AuteurRepository auteurRepo;
    @Autowired
    ConferenceRepository conferenceRepo;

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

    @Test
    public void test_createAndRetrieveSoumission() {
        Auteur auteur = new Auteur("TestNom", "TestPrenom", "TestInfos");
        auteurRepo.save(auteur);

        Conference conference = new Conference("TestConference", "TestTheme", "Active", new Date(), new Date(), null);
        conferenceRepo.save(conference);

        Soumission soumission = new Soumission("TestSoumission", "TestDescription", auteur, conference);
        soumissionService.addSoumission(soumission);

        Soumission retrievedSoumission = soumissionService.findByNom("TestSoumission");
        assert retrievedSoumission != null;
        assert retrievedSoumission.getNom().equals("TestSoumission");
        assert retrievedSoumission.getDescription().equals("TestDescription");
    }

    @Test
    public void test_createAndRetrieveDetailsSoumission() {
        DetailsSoumission detailsSoumission = new DetailsSoumission("2023-10-01", "2023-10-02");
        detailsSoumissionService.addDetailsSoumission(detailsSoumission);

        DetailsSoumission retrievedDetailsSoumission = detailsSoumissionService.findByDateDeSoumission("2023-10-01");
        assert retrievedDetailsSoumission != null;
        assert retrievedDetailsSoumission.getDateDeSoumission().equals("2023-10-01");
        assert retrievedDetailsSoumission.getDateDeModification().equals("2023-10-02");
    }
}