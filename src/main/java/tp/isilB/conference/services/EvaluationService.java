package tp.isilB.conference.services;
import org.springframework.stereotype.Service;
import tp.isilB.conference.entities.Evaluation;
import tp.isilB.conference.repositories.EvaluationRepository;
import java.util.List;
@Service
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;
    public EvaluationService(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    public Evaluation findById(Long id) {
        return evaluationRepository.findById(id).orElse(null);
    }
    public Evaluation addEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }
    public List<Evaluation> findAllEvaluations() {
        return evaluationRepository.findAll();
    }

}