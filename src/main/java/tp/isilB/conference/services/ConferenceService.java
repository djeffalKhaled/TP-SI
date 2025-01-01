package tp.isilB.conference.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tp.isilB.conference.entities.Conference;
import tp.isilB.conference.entities.UserApp;
import tp.isilB.conference.repositories.ConferenceRepository;
import tp.isilB.conference.repositories.UserAppRepository;

import java.util.List;

@Service
public class ConferenceService {
    @Autowired
    private ConferenceRepository conferenceRepository;

    public Conference addConference(Conference conference) {
        return conferenceRepository.save(conference);
    }

    public List<Conference> findAllConferences() {
        return (List<Conference>)conferenceRepository.findAll();
    }

}
