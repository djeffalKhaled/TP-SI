package tp.isilB.conference.services;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tp.isilB.conference.repositories.RoleRepository;
import tp.isilB.conference.repositories.UserAppRepository;
import tp.isilB.conference.entities.UserApp;

import java.util.List;
import java.util.Optional;

@Service
public class UserAppService implements UserDetailsService {
    @Autowired
    private UserAppRepository userAppRepository;

    public UserApp addUserApp(@Valid UserApp userApp) {
        return userAppRepository.save(userApp);
    }

    public UserApp findUserAppByEmail(String email) {
        return (UserApp) userAppRepository.findByEmail(email);
    }

    public List<UserApp> findAllUserApp() {
        return (List<UserApp>)userAppRepository.findAll();
    }

    public boolean isEditeur(UserApp user) {
        return user.getRoles().stream().anyMatch(role -> role.getNomRole().equals("Editeur"));
    }

    public boolean isEvaluateur(UserApp user) {
        return user.getRoles().stream().anyMatch(role -> role.getNomRole().equals("Evaluateur"));
    }

    public boolean isAuteur(UserApp user) {
        return user.getRoles().stream().anyMatch(role -> role.getNomRole().equals("Auteur"));
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserApp user = userAppRepository.findByEmail(email);

        if (user != null) {
            return User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .build();
        } else {
            throw new UsernameNotFoundException("Email " + email + " pas trouvé!");
        }
    }

}
