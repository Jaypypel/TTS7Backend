package com.TTS.DbWebAPIs.Controller;


import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Exceptions.AlreadyExistException;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/user")
@RequiredArgsConstructor




@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;


//    @PostMapping("/register")
//    public boolean Registration(@RequestBody User inputUser){
//        return userService.registerUser(inputUser);
//    }

    //tested again at 11:24 am on 3rd Oct
    @PostMapping("/register")
    public ResponseEntity<APIResponse> Registration(@RequestBody User inputUser) {
            if(inputUser.getUsername() == null || inputUser.getFullName() ==null || inputUser.getPassword() ==null || inputUser.getMobileNo() ==null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse("check User attributes can't be empty or null",null));
            }
//        boolean isRegistered = userService.registerUser(inputUser);
//          Us
//        if (isRegistered) {
//            // If user registration is successful, return HTTP 200 OK with a success message.
//            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
//        } else {
//            // If the user already exists, return HTTP 409 Conflict with an appropriate message.
//            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
//        }
        User user = userService.registerUser(inputUser);
            System.out.println(user);
            if(user != null)  return ResponseEntity.ok(new APIResponse("User registered successfully",null));
            else return ResponseEntity.status(HttpStatus.CONFLICT).body(new APIResponse("User already exists",null));



    }

//    @GetMapping("/login")
//    public boolean Login(String username, String password) throws Exception {
//        return userService.getUserbyUsernameAndPassword(username,password).isPresent();
//    }
@GetMapping("/login")
public ResponseEntity<APIResponse> login(@RequestParam String username, @RequestParam String password) throws Exception {
    boolean userExists = userService.getUserbyUsernameAndPassword(username, password).isPresent();

    if (userExists) {
        // If user exists, return HTTP 200 OK with a success message.
       // return new ResponseEntity<>("Login successful", HttpStatus.OK);
        return ResponseEntity.ok(new APIResponse("Login succssful",null));
    } else {
        // If user does not exist, return HTTP 401 Unauthorized with an error message.
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new APIResponse("Invalid Login details", null));
    }
}


}
