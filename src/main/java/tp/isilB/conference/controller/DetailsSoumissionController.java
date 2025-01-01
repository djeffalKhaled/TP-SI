package tp.isilB.conference.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp.isilB.conference.entities.DetailsSoumission;
import tp.isilB.conference.entities.Soumission;
import tp.isilB.conference.services.DetailsSoumissionService;

import java.util.List;

@RestController
@RequestMapping("api/v1/soumission/detailsoumission")
public class DetailsSoumissionController {
    private final DetailsSoumissionService detailsSoumissionService;

    public DetailsSoumissionController(DetailsSoumissionService detailsSoumissionService) {
        this.detailsSoumissionService = detailsSoumissionService;
    }

    @GetMapping
    public ResponseEntity<List<DetailsSoumission>> getDetailsSoumission() {
        List<DetailsSoumission> detailsSoumissions = (List<DetailsSoumission>) detailsSoumissionService.findAllDetailsSoumission();
        for (DetailsSoumission detailsSoumission : detailsSoumissions) {
            System.out.println("GetdetailsSoumission: " + detailsSoumission.toString());
        }
        return ResponseEntity.ok(detailsSoumissions);
    }

    @GetMapping("/{dateDeSoumission}")
    public ResponseEntity<List<DetailsSoumission>> getDetailsSoumissionDateDeSoumission(@PathVariable("dateDeSoumission") String dateDeSoumission) {
        List<DetailsSoumission> detailsSoumissionDateDeSoumission = (List<DetailsSoumission>) detailsSoumissionService.findByDateDeSoumission(dateDeSoumission);
        for (DetailsSoumission detailsSoumission : detailsSoumissionDateDeSoumission) {
            System.out.println("GetdetailsSoumissionDateDeSoumission: " + detailsSoumission.toString());
        }
        return ResponseEntity.ok(detailsSoumissionDateDeSoumission);
    }

    
    @GetMapping("/{dateDeModification}")
    public ResponseEntity<List<DetailsSoumission>> getDetailsSoumissionDateDeModification(@PathVariable("dateDeModification") String dateDeModification) {
        List<DetailsSoumission> detailsSoumissionDateDeModification = (List<DetailsSoumission>) detailsSoumissionService.findByDateDeModification(dateDeModification);
        for (DetailsSoumission detailsSoumission : detailsSoumissionDateDeModification) {
            System.out.println("GetdetailsSoumissionDateDeModification: " + detailsSoumission.toString());
        }
        return ResponseEntity.ok(detailsSoumissionDateDeModification);
    }

}
