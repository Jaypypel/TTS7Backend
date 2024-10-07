package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.TimeShareOtherActivity;
import com.TTS.DbWebAPIs.Service.TimeShareOtherActivityServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

//    @PostMapping("/add/timeshareactivity/{username}/{activityName}/{date}/{endTime}/{timeDifference}/{description}/{createdOn}")
//    ResponseEntity<TimeShareOtherActivity> addOtherActivity(@PathVariable String username,@PathVariable String activityName,
//                                                            @PathVariable LocalDateTime date,@PathVariable LocalTime startTime,
//                                                            @PathVariable LocalTime endTime,@PathVariable String timeDifference,
//                                                            @PathVariable String description,@PathVariable LocalTime createdOn){
//        TimeShareOtherActivity timeShareOtherActivity = timeShareOtherActivityService.addOtherActivity(username,activityName,date,startTime,endTime,timeDifference,description,createdOn);
//        return ResponseEntity.ok(timeShareOtherActivity);
//    }
//@PostMapping("/add/timeshareactivity/{username}/{activityName}/{dateTime}/{startTime}/{endTime}/{timeDifference}/{description}/{createdOn}")
//public ResponseEntity<TimeShareOtherActivity> addOtherActivity(
//        @PathVariable String username,
//        @PathVariable String activityName,
//        @PathVariable("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
//        @PathVariable("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
//        @PathVariable("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime,
//        @PathVariable String timeDifference,
//        @PathVariable String description,
//        @PathVariable("createdOn") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime createdOn) {
//
//    TimeShareOtherActivity timeShareOtherActivity = timeShareOtherActivityService.addOtherActivity(
//            username,
//            activityName,
//            dateTime,
//            startTime,
//            endTime,
//            timeDifference,
//            description,
//            createdOn);
//
//    return ResponseEntity.ok(timeShareOtherActivity);
//}
@PostMapping("/add/timeshareactivity/{username}/{activityName}/{dateTime}/{startTime}/{endTime}/{timeDifference}/{description}/{createdOn}")
public ResponseEntity<TimeShareOtherActivity> addOtherActivity(
        @PathVariable String username,
        @PathVariable String activityName,
        @PathVariable("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
        @PathVariable("startTime") String startTime, // Changed to String for trimming
        @PathVariable("endTime") String endTime,     // Changed to String for trimming
        @PathVariable String timeDifference,
        @PathVariable String description,
        @PathVariable("createdOn") String createdOn) { // Changed to String for trimming

    // Trim the inputs to remove unwanted characters
    startTime = startTime.trim();
    endTime = endTime.trim();
    createdOn = createdOn.trim();

    LocalTime parsedStartTime = LocalTime.parse(startTime);
    LocalTime parsedEndTime = LocalTime.parse(endTime);
    LocalTime parsedCreatedOn = LocalTime.parse(createdOn);

    TimeShareOtherActivity timeShareOtherActivity = timeShareOtherActivityService.addOtherActivity(
            username,
            activityName,
            dateTime,
            parsedStartTime,
            parsedEndTime,
            timeDifference,
            description,
            parsedCreatedOn);

    return ResponseEntity.ok(timeShareOtherActivity);
}


}
