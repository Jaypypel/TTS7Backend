package com.TTS.DbWebAPIs.Controller;


import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;


//    @PostMapping("/register")
//    public boolean Registration(@RequestBody User inputUser){
//        return userService.registerUser(inputUser);
//    }

    @PostMapping("/register")
    public ResponseEntity<String> Registration(@RequestBody User inputUser) {
        boolean isRegistered = userService.registerUser(inputUser);

        if (isRegistered) {
            // If user registration is successful, return HTTP 200 OK with a success message.
            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        } else {
            // If the user already exists, return HTTP 409 Conflict with an appropriate message.
            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
        }
    }

//    @GetMapping("/login")
//    public boolean Login(String username, String password) throws Exception {
//        return userService.getUserbyUsernameAndPassword(username,password).isPresent();
//    }
@GetMapping("/login")
public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) throws Exception {
    boolean userExists = userService.getUserbyUsernameAndPassword(username, password).isPresent();

    if (userExists) {
        // If user exists, return HTTP 200 OK with a success message.
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    } else {
        // If user does not exist, return HTTP 401 Unauthorized with an error message.
        return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
    }
}


}
