package tp.isilB.conference.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tp.isilB.conference.entities.Evaluateur;

public interface EvaluateurRepository extends JpaRepository<Evaluateur, Long> {
    Evaluateur findByNom(String nom);
}