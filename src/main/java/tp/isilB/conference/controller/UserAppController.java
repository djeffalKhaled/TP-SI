package tp.isilB.conference.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public ResponseEntity<UserApp> createUser(@RequestBody UserApp userApp) {
        UserApp createdUser = userAppService.addUserApp(userApp);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<UserApp>> getUserApps() {
        List<UserApp> users = userAppService.findAllUserApp();
        for (UserApp user : userAppService.findAllUserApp()) {
            System.out.println("GetUserApp: " + user.toString());
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserApp> getUserApp(@PathVariable("email") String email) {
        UserApp userApp = userAppService.findUserAppByEmail(email);
        return ResponseEntity.ok(userApp);
    }


}
