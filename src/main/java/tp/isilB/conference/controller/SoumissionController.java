package tp.isilB.conference.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tp.isilB.conference.entities.Auteur;
import tp.isilB.conference.entities.DetailsSoumission;
import tp.isilB.conference.entities.Soumission;
import tp.isilB.conference.entities.UserApp;
import tp.isilB.conference.repositories.AuteurRepository;
import tp.isilB.conference.repositories.DetailsSoumissionRepository;
import tp.isilB.conference.repositories.SoumissionRepository;
import tp.isilB.conference.services.SoumissionService;

import java.time.LocalDate;
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

    @GetMapping("/{nom}")
    public ResponseEntity<Soumission> getSoumissionByNom(@PathVariable("nom") String nom) {
        Soumission soumission = soumissionService.findByNom(nom);
        return ResponseEntity.ok(soumission);
    }

    @PreAuthorize("hasRole('AUTEUR')")
    @DeleteMapping("/{nom}")
    public ResponseEntity<Soumission> deleteSoumissionByNom(@PathVariable("nom") String nom) {
        Soumission soumission = soumissionService.findByNom(nom);
        return ResponseEntity.ok(soumission);
    }

    @PreAuthorize("hasRole('AUTEUR')")
    @PutMapping("/{nom}")
    public ResponseEntity<Soumission> editSoumissionByNom(@PathVariable("nom") String nom, @RequestBody Soumission soumission) {
        Soumission updateSoumission = soumissionService.findByNom(nom);
        DetailsSoumission updateDetailsSoumission = new DetailsSoumission();
        updateSoumission.setNom(soumission.getNom());
        updateSoumission.setAuteur(updateSoumission.getAuteur());
        updateDetailsSoumission.setDateDeModification(String.valueOf(LocalDate.now()));
        return ResponseEntity.ok(updateSoumission);
    }
}
