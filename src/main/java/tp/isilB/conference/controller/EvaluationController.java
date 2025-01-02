package tp.isilB.conference.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tp.isilB.conference.entities.Evaluateur;
import tp.isilB.conference.entities.Evaluation;
import tp.isilB.conference.entities.Soumission;
import tp.isilB.conference.repositories.EvaluateurRepository;
import tp.isilB.conference.repositories.EvaluationRepository;
import tp.isilB.conference.services.EvaluationService;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("api/v1/evaluation")
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private EvaluateurRepository evaluateurRepo;

    @PreAuthorize("hasAnyRole('EVALUATEUR')")
    @PostMapping
    public ResponseEntity<Evaluation> createEvaluation(@RequestBody Evaluation evaluation) {
        Evaluation savedEvaluation = evaluationService.addEvaluation(evaluation);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvaluation);
    }

    @PreAuthorize("hasAnyRole('EVALUATEUR', 'EDITEUR')")
    @GetMapping
    public ResponseEntity<List<Evaluation>> getAllEvaluations() {
        List<Evaluation> evaluations = evaluationService.findAllEvaluations();
        return ResponseEntity.ok(evaluations);
    }

    @PreAuthorize("hasAnyRole('EDITEUR')")
    @PutMapping("/evaluateur/{nom}") //
    public ResponseEntity<Evaluation> affecteEvaluationEvaluateur(@RequestBody Evaluation evaluation, @PathVariable("nom") String nom) {
        Evaluateur evaluateur = evaluateurRepo.findByNom(nom);
        evaluation.setEvaluateur(evaluateur);
        evaluationService.addEvaluation(evaluation);
        return ResponseEntity.status(HttpStatus.CREATED).body(evaluation);
    }

    @PreAuthorize("hasAnyRole('EVALUATEUR', 'EDITEUR')")
    @GetMapping("/evaluateur") // Retourne les evaluateurs de tout les evaluations
    public ResponseEntity<List<Evaluateur>> getEvaluationEvaluateur() {
        List<Evaluation> evaluations = evaluationService.findAllEvaluations();
        List<Evaluateur> evaluateurs = new ArrayList<>();
        for (Evaluation evaluation : evaluations) {
            evaluateurs.add(evaluation.getEvaluateur());
        }
        return ResponseEntity.ok(evaluateurs);
    }
}