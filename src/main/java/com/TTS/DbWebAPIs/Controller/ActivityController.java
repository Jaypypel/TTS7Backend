package com.TTS.DbWebAPIs.Controller;


import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Exceptions.AlreadyExistException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.ActivityServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;

import java.time.LocalDate;

import java.util.List;

//tested all the endpoints
@RequiredArgsConstructor
@RestController
@RequestMapping("/activities")
public class ActivityController {


       private final ActivityServiceInterface activityService;

       //tested at 1:45pm on 7 oct
        @GetMapping("/names")
        ResponseEntity<?> getActivtiesNames() throws SQLException {
            List<String> activities = activityService.getActivityNames();
            return ResponseEntity.ok(new APIResponse("successful",activities));

        }

    @GetMapping("/activityNames")
    ResponseEntity<?> getActivtiesNamesByUserName(@RequestParam(name = "username" , required = true) String username) throws SQLException {
       try {
           List<String> activities = activityService.getActivityNamesByUsername(username);
           return ResponseEntity.ok(new APIResponse("successful",activities));
       }catch (RuntimeException e){
           return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
       }

    }

    //tested at 2:45pm on 7 oct
    @GetMapping("/names/{username}")
    ResponseEntity<?>  getActivityList(@PathVariable String username) throws SQLException{
        try {
            List<Activity>  activities = activityService.getActivityList(username);
            return ResponseEntity.ok(new APIResponse("successful",activities));

        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        }

        //tested already
        @GetMapping("/activityCount/{username}/{startDate}/{endDate}")
        ResponseEntity<Integer>  getActivityCount(@PathVariable String username, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate) throws SQLException{
            Integer activityCount = activityService.getActivityCount(username, startDate, endDate);
            return ResponseEntity.ok(activityCount);
        }

        //tested already
        @PostMapping("/activity")
        ResponseEntity<?>  addActivity(@RequestParam String username, @RequestParam String actvtyNme, @RequestParam String createdOn){
               try {
                   Activity activity = activityService.addActivity(username,actvtyNme,createdOn);
                   return ResponseEntity.ok(new APIResponse("successful","-"));
               } catch (RuntimeException e) {
                   return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
               }

        }

        //tested already
        @GetMapping("/activity/{name}")
        ResponseEntity<Activity>   getActivity(@PathVariable String name){
            Activity activity = activityService.getActivity(name);
            return ResponseEntity.ok(activity);
        }

}
