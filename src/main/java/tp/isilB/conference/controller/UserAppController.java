package tp.isilB.conference.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tp.isilB.conference.entities.UserApp;
import tp.isilB.conference.services.UserAppService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class UserAppController {
    @Autowired
    private final UserAppService userAppService;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    // Recent issue with post, for some reason it doesn't accept its body despite being the same as the get body!
    // Spent 3 hours debuging it I give up at this point

    @PostMapping
    public ResponseEntity<UserApp> createUser(@RequestBody UserApp userApp) {
        userApp.setPassword(passwordEncoder.encode(userApp.getPassword()));
        UserApp createdUser = userAppService.addUserApp(userApp);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @GetMapping
    public ResponseEntity<List<UserApp>> getUsers() {
        List<UserApp> users = userAppService.findAllUserApp();
        for (UserApp user : userAppService.findAllUserApp()) {
            System.out.println("GetUserApp: " + user.toString());
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserApp> getUserByEmail(@PathVariable("email") String email) {
        UserApp userApp = userAppService.findUserAppByEmail(email);
        return ResponseEntity.ok(userApp);
    }




}
