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

import java.sql.SQLException;
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
       try{
           List<String> usernames = userService.getUsernameList();
           if (usernames == null || usernames.isEmpty()){
               return ResponseEntity
                       .status(HttpStatus.NO_CONTENT)
                       .body(new APIResponse<>("No username found", null));
           }
           return ResponseEntity.ok(new APIResponse("successful",usernames));
       }
        catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occured while fetching activity names. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    //tested again at 11:24 am on 3rd Oct
    @PostMapping("/register")
    public ResponseEntity<?> Registration(@RequestBody User inputUser) {
            try {
                User user = userService.registerUser(inputUser);
                return ResponseEntity.ok(new APIResponse("successful","-"));
            } catch (AlreadyExistException e){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new APIResponse("Error : "+e.getMessage(),"-"));
            } catch (SQLException ex){
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new APIResponse<>("An error occured while fetching activity names. Please try again later.",null));
            } catch (Exception ex){
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
            }

    }

@GetMapping("/login")
public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) throws Exception {
    try{
        boolean userExists = userService.areUserCredentialValid(username, password).isPresent();
        if (userExists) {
            // If user exists, return HTTP 200 OK with a success message.
            // return new ResponseEntity<>("Login successful", HttpStatus.OK);
            return ResponseEntity.ok(new APIResponse<>("Successful",null));
        } else {
            // If user does not exist, return HTTP 401 Unauthorized with an error message.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new APIResponse<>("Invalid Login details", null));
        }
    }catch (SQLException ex){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new APIResponse<>("An error occured while fetching activity names. Please try again later.",null));
    } catch (Exception ex){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
    }
}


}
