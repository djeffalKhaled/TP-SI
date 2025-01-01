package tp.isilB.conference.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final SoumissionRepository soumissionRepository;

    public SoumissionController(SoumissionService soumissionAppService, SoumissionRepository soumissionRepository, DetailsSoumissionRepository detailsSoumissionRepository) {
        this.soumissionService = soumissionAppService;
        this.soumissionRepository = soumissionRepository;

    }
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
    @GetMapping("/auteur")
    public ResponseEntity<Soumission> getSoumissionByAuteur(@PathVariable("auteur") Auteur auteur) {
        Soumission soumission = soumissionService.findByAuteur(auteur);
        return ResponseEntity.ok(soumission);
    }

    @DeleteMapping("/{nom}")
    public ResponseEntity<Soumission> deleteSoumissionByNom(@PathVariable("nom") String nom) {
        Soumission soumission = soumissionService.findByNom(nom);
        return ResponseEntity.ok(soumission);
    }

    @DeleteMapping("/auteur")
    public ResponseEntity<Soumission> deleteSoumissionByAuteur(@PathVariable("auteur") Auteur auteur) {
        Soumission soumission = soumissionService.findByAuteur(auteur);
        return ResponseEntity.ok(soumission);
    }

    @PutMapping("/{nom}")
    public ResponseEntity<Soumission> editSoumissionByNom(@PathVariable("nom") String nom, @RequestBody Soumission soumission) {
        Soumission updateSoumission = soumissionRepository.findByNom(nom);
        DetailsSoumission updateDetailsSoumission = new DetailsSoumission();
        updateSoumission.setNom(soumission.getNom());
        updateSoumission.setAuteur(updateSoumission.getAuteur());
        updateDetailsSoumission.setDateDeModification(String.valueOf(LocalDate.now()));
        return ResponseEntity.ok(updateSoumission);
    }
}
