package tp.isilB.conference.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.isilB.conference.entities.DetailsSoumission;
import tp.isilB.conference.entities.Soumission;
import tp.isilB.conference.repositories.DetailsSoumissionRepository;

import java.util.List;

@Service
public class DetailsSoumissionService {
    @Autowired
    private DetailsSoumissionRepository detailsSoumissionRepository;

    public DetailsSoumission addDetailsSoumission(DetailsSoumission detailsSoumission) {
        return detailsSoumissionRepository.save(detailsSoumission);
    }

    public DetailsSoumission findById(int id) {
        return detailsSoumissionRepository.findById(id);
    }

    public DetailsSoumission findByDateDeSoumission(String dateDeSoumission){
        return detailsSoumissionRepository.findByDateDeSoumission(dateDeSoumission);
    }

    public DetailsSoumission findByDateDeModification(String dateDeModification) {
        return detailsSoumissionRepository.findByDateDeModification(dateDeModification);
    }

    public List<DetailsSoumission> findAllDetailsSoumission() {
        return (List<DetailsSoumission>) detailsSoumissionRepository.findAllDetailsSoumission();
    }
}
