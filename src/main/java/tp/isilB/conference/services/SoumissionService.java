package tp.isilB.conference.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.isilB.conference.entities.Auteur;
import tp.isilB.conference.entities.Soumission;
import tp.isilB.conference.entities.UserApp;
import tp.isilB.conference.repositories.SoumissionRepository;
import tp.isilB.conference.repositories.UserAppRepository;

import java.util.List;

@Service
public class SoumissionService {
    @Autowired
    private SoumissionRepository soumissionRepository;

    public Soumission addSoumission(Soumission soumission) {
        return soumissionRepository.save(soumission);
    }

    public Soumission findByAuteur (Auteur auteur){
        return (Soumission) soumissionRepository.findByAuteur(auteur);
    }

    public Soumission findByTitre (String titre){
        return soumissionRepository.findByTitre(titre);
    }

    public List<Soumission> findAllSoumissions(){
        return (List<Soumission>) soumissionRepository.findAll();
    }
}

