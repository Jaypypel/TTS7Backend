package com.TTS.DbWebAPIs.Controller;


import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Exceptions.AlreadyExistException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
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
        ResponseEntity<List<String>> getActivtiesNames() throws SQLException {
            List<String> activities = activityService.getActivityNames();
            return ResponseEntity.ok(activities);

        }

       //tested at 2:45pm on 7 oct
        @GetMapping("/names/{username}")
        ResponseEntity<List<Activity>>  getActivityList(@PathVariable String username) throws SQLException{
            List<Activity>  activities = activityService.getActivityList(username);
            return ResponseEntity.ok(activities);
        }

        //tested already
        @GetMapping("/activityCount/{username}/{startDate}/{endDate}")
        ResponseEntity<Integer>  getActivityCount(@PathVariable String username, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate) throws SQLException{
            Integer activityCount = activityService.getActivityCount(username, startDate, endDate);
            return ResponseEntity.ok(activityCount);
        }

        //tested already
        @PostMapping("/activity/{username}/{actvtyNme}/{createdOn}")
        ResponseEntity<Activity>  addActivity(@PathVariable String username, @PathVariable String actvtyNme, @PathVariable LocalDate createdOn){
                Activity activity = activityService.addActivity(username,actvtyNme,createdOn);
                return ResponseEntity.ok(activity);

        }

        //tested already
        @GetMapping("/activity/{name}")
        ResponseEntity<Activity>   getActivity(@PathVariable String name){
            Activity activity = activityService.getActivity(name);
            return ResponseEntity.ok(activity);
        }

}
