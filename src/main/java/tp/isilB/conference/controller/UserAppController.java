package tp.isilB.conference.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tp.isilB.conference.entities.UserApp;
import tp.isilB.conference.services.UserAppService;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserAppController {
    private final UserAppService userAppService;
    public UserAppController(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    // Recent issue with post, for some reason it doesn't accept its body despite being the same as the get body!
    // Spent 3 hours debuging it I give up at this point

    @PostMapping
    public ResponseEntity<UserApp> createUser(@RequestBody UserApp userApp) {
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
