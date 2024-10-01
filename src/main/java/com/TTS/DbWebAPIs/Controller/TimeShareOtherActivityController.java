package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.TimeShareOtherActivity;
import com.TTS.DbWebAPIs.Service.TimeShareOtherActivityServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("timeshareotheractivities")
public class TimeShareOtherActivityController {

    private  final TimeShareOtherActivityServiceInterface timeShareOtherActivityService;

    @PostMapping("/add/timeshareactivity")
    ResponseEntity<TimeShareOtherActivity> addOtherActivity(Long userId, String activityName, LocalDateTime date, LocalTime startTime, LocalTime endTime, String timeDifference, String description, LocalTime createdOn){
        TimeShareOtherActivity timeShareOtherActivity = timeShareOtherActivityService.addOtherActivity(userId,activityName,date,startTime,endTime,timeDifference,description,createdOn);
        return ResponseEntity.ok(timeShareOtherActivity);
    }

}
