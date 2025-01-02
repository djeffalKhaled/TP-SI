package tp.isilB.conference.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tp.isilB.conference.entities.Conference;
import tp.isilB.conference.entities.Editeur;
import tp.isilB.conference.entities.UserApp;
import tp.isilB.conference.services.ConferenceService;
import tp.isilB.conference.services.UserAppService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/conference")
public class ConferenceController {
    private final UserAppService userAppService;
    public final ConferenceService conferenceService;
    public ConferenceController(UserAppService userAppService, ConferenceService conferenceService) {
        this.userAppService = userAppService; this.conferenceService = conferenceService;
    }
    @PreAuthorize("hasRole('EDITEUR')") // Juste les editeur peut crée les conferences
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

    @GetMapping("/editeur")
    public ResponseEntity<List<Editeur>> getEditeurConferences() {
        List<Conference> conferences = conferenceService.findAllConferences();
        List<Editeur> editeurs = new ArrayList<Editeur>();
        for (Conference conference : conferences) {
            editeurs.add(conference.getEditeur());
        }
        return ResponseEntity.ok(editeurs);
    }

}
