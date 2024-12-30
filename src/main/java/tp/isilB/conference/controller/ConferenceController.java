package tp.isilB.conference.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp.isilB.conference.entities.Conference;
import tp.isilB.conference.entities.UserApp;
import tp.isilB.conference.services.ConferenceService;
import tp.isilB.conference.services.UserAppService;

import java.util.List;

@RestController
@RequestMapping("api/v1/conference")
public class ConferenceController {
    private final UserAppService userAppService;
    public final ConferenceService conferenceService;
    public ConferenceController(UserAppService userAppService, ConferenceService conferenceService) {
        this.userAppService = userAppService; this.conferenceService = conferenceService;
    }

    @PostMapping // JUST FOR DEBUGGING
    public ResponseEntity<Conference> createConference(@RequestBody Conference conference) {
        Conference newConference = conferenceService.addConference(conference);
        return ResponseEntity.status(HttpStatus.CREATED).body(newConference);
    }
    @GetMapping
    public ResponseEntity<List<Conference>> getConferences() {
        List<Conference> conferences = conferenceService.findAllConferences();
        return ResponseEntity.ok(conferences);
    }

    @PostMapping("/{email}")
    public ResponseEntity<Conference> editorCreateConference(@PathVariable("email") String email, @RequestBody Conference conference) {
        UserApp LoggedUser = userAppService.findUserAppByEmail(email);
        if (userAppService.isEditeur(LoggedUser)) {
            // Allow conference creation
            System.out.println("Editeur as crée une conference");
            Conference newConference = conferenceService.addConference(conference);
            return ResponseEntity.status(HttpStatus.CREATED).body(newConference);
        } else {
            System.out.println("Cet utilisateur n’est pas un éditeur et n’a donc aucun privilège de création de conférence!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserApp> getUserByEmail(@PathVariable("email") String email) {
        UserApp userApp = userAppService.findUserAppByEmail(email);
        return ResponseEntity.ok(userApp);
    }
}
