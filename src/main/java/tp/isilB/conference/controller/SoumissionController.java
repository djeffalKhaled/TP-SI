package tp.isilB.conference.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tp.isilB.conference.entities.*;
import tp.isilB.conference.repositories.AuteurRepository;
import tp.isilB.conference.repositories.DetailsSoumissionRepository;
import tp.isilB.conference.repositories.SoumissionRepository;
import tp.isilB.conference.services.SoumissionService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/soumission")
public class SoumissionController {
    private final SoumissionService soumissionService;

    public SoumissionController(SoumissionService soumissionAppService) {
        this.soumissionService = soumissionAppService;

    }

    @PreAuthorize("hasRole('AUTEUR')")
    @PostMapping
    public ResponseEntity<Soumission> createSoumission(@RequestBody Soumission soumission) {
        Soumission createdSoumission = soumissionService.addSoumission(soumission);
        DetailsSoumission detailsSoumission = new DetailsSoumission();
        detailsSoumission.setDateDeSoumission(String.valueOf(LocalDate.now()));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSoumission);
    }

    @GetMapping
    public ResponseEntity<List<Soumission>> getSoumissionApps() {
        List<Soumission> soumissions = (List<Soumission>) soumissionService.findAllSoumissions();
        for (Soumission soumission : soumissions) {
            System.out.println("GetSoumission: " + soumission.toString());
        }
        return ResponseEntity.ok(soumissions);
    }

    @GetMapping("/{titre}")
    public ResponseEntity<Soumission> getSoumissionByTitre(@PathVariable("titre") String titre) {
        Soumission soumission = soumissionService.findByTitre(titre);
        return ResponseEntity.ok(soumission);
    }

    @PreAuthorize("hasRole('AUTEUR')")
    @DeleteMapping("/{titre}")
    public ResponseEntity<Soumission> deleteSoumissionByTitre(@PathVariable("titre") String titre) {
        Soumission soumission = soumissionService.findByTitre(titre);
        return ResponseEntity.ok(soumission);
    }

    @PreAuthorize("hasRole('AUTEUR')")
    @PutMapping("/{titre}")
    public ResponseEntity<Soumission> editSoumissionByTitre(@PathVariable("titre") String titre, @RequestBody Soumission soumission) {
        Soumission updateSoumission = soumissionService.findByTitre(titre);
        DetailsSoumission updateDetailsSoumission = new DetailsSoumission();
        updateSoumission.setTitre(soumission.getTitre());
        updateSoumission.setAuteur(updateSoumission.getAuteur());
        updateDetailsSoumission.setDateDeModification(String.valueOf(LocalDate.now()));
        return ResponseEntity.ok(updateSoumission);
    }


    @GetMapping("/auteur")
    public ResponseEntity<List<Auteur>> getAuteurSoumission() {
        List<Soumission> soumissions = soumissionService.findAllSoumissions();
        List<Auteur> auteurs = new ArrayList<Auteur>();
        for (Soumission soumission : soumissions) {
            auteurs.add(soumission.getAuteur());
        }
        return ResponseEntity.ok(auteurs);
    }
}
