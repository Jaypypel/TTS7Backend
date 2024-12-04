package com.TTS.DbWebAPIs.Controller;


import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Exceptions.AlreadyExistException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/user")
@RequiredArgsConstructor



public class UserController {


    private final UserService userService;


//    @PostMapping("/register")
//    public boolean Registration(@RequestBody User inputUser){
//        return userService.registerUser(inputUser);
//    }

    @GetMapping("/list")
    public ResponseEntity<APIResponse> getUsernameList(){
        List<String> usernames = userService.getUsernameList();
        return ResponseEntity.ok(new APIResponse("successful",usernames));
    }

    //tested again at 11:24 am on 3rd Oct
    @PostMapping("/register")
    public ResponseEntity<?> Registration(@RequestBody User inputUser) {
            try {
                if(inputUser.getUsername() == null || inputUser.getFullName() ==null || inputUser.getPassword() ==null || inputUser.getMobileNo() ==null){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("check User attributes can't be empty or null");
                }
                User user = userService.registerUser(inputUser);
                return ResponseEntity.ok(new APIResponse("successful","-"));
            } catch (AlreadyExistException e){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new APIResponse("Error : "+e.getMessage(),"-"));
            }
    }

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
