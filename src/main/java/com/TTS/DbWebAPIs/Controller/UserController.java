package com.TTS.DbWebAPIs.Controller;


import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Exceptions.*;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/app/user")
@RequiredArgsConstructor



public class UserController {


    private final UserService userService;



    @GetMapping("/list")
    public ResponseEntity<APIResponse> getUsernameList() throws DatabaseException, InternalServerException{
           List<String> usernames = userService.getUsernameList();
           if (usernames == null || usernames.isEmpty()){
               return ResponseEntity
                       .status(HttpStatus.NO_CONTENT)
                       .body(new APIResponse<>("No username found", null));
           }
           return ResponseEntity.ok(new APIResponse<>("successful",usernames));
    }

    //tested again at 11:24 am on 3rd Oct
    @PostMapping("/register")
    public ResponseEntity<?> Registration(@RequestBody User inputUser)
            throws DatabaseException, UserAlreadyExistsException, InternalServerException {
        userService.registerUser(inputUser);
        return ResponseEntity.ok(new APIResponse<>("successful","-"));
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) throws
            UserNotFoundException, InvalidLoginDetailsException, DatabaseException, InternalServerException {
        String validatedUsername = userService.isUsernameExist(username).getUsername();
        userService.areUserCredentialValid(validatedUsername, password);
        return ResponseEntity.ok(new APIResponse<>("Successful", null));
    }
}
