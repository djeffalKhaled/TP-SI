package tp.isilB.conference.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp.isilB.conference.entities.Soumission;
import tp.isilB.conference.entities.UserApp;
import tp.isilB.conference.services.SoumissionService;

import java.util.List;

@RestController
@RequestMapping("api/v1/soumission")
public class SoumissionController {
    private final SoumissionService soumissionService;

    public SoumissionController(SoumissionService soumissionAppService) {
        this.soumissionService = soumissionAppService;
    }
    @PostMapping
    public ResponseEntity<Soumission> createSoumission(@RequestBody Soumission soumission) {
        Soumission createdSoumission = soumissionService.addSoumission(soumission);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSoumission);
    }

//    @GetMapping
//    public ResponseEntity<List<Soumission>> getSoumissionApps() {
//        List<UserApp> users = soumissionService.findAllUserApp();
//        for (UserApp user : soumissionService.findAllUserApp()) {
//            System.out.println("GetUserApp: " + user.toString());
//        }
//        return ResponseEntity.ok(users);
//    }
}
