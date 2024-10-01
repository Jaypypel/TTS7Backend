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
import java.util.ArrayList;;
import java.util.Collections;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/activities")
public class ActivityController {


       private final ActivityServiceInterface activityService;

        @GetMapping("/names")
        ResponseEntity<?> getActivtiesNames() throws SQLException {
            List<String> activities = activityService.getActivityNames();
            return ResponseEntity.ok(activities);

        }

        @GetMapping("/names/{userId}")
        ResponseEntity<List<Activity>>  getActivityList(@PathVariable Long userId) throws SQLException{
            List<Activity>  activities = activityService.getActivityList(userId);
            return ResponseEntity.ok(activities);
        }

        @GetMapping("/activityCount/{userId}/{startDate}/{endDate}")
        ResponseEntity<Integer>  getActivityCount(@PathVariable Long userId, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate) throws SQLException{
            Integer activityCount = activityService.getActivityCount(userId, startDate, endDate);
            return ResponseEntity.ok(activityCount);
        }

        @PostMapping("/activity/{userId}/{actvtyNme}/{createdOn}")
        ResponseEntity<Activity>  addActivity(@PathVariable Long userId, @PathVariable String actvtyNme, @PathVariable LocalDate createdOn){
                Activity activity = activityService.addActivity(userId,actvtyNme,createdOn);
                return ResponseEntity.ok(activity);

        }

}
