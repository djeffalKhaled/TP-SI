
package tp.isilB.conference.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import tp.isilB.conference.entities.Evaluation;
import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    List<Evaluation> findByEtat(String etat);
    List<Evaluation> findBySoumissionId(int soumissionId);
    List<Evaluation> findByEvaluateurId(int evaluateurId);
    List<Evaluation> findByNote(int note);
}