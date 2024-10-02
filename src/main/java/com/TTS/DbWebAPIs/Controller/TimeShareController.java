package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Entity.TimeShareMeasurables;
import com.TTS.DbWebAPIs.Service.TimeShareServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("timeshares")
public class TimeShareController {

    private  final TimeShareServiceInterface timeShareService;

    @GetMapping("/list/{userId}/{startDate}/{endDate}")
    ResponseEntity<List<TimeShare>> getTimeShareList(@PathVariable Long userId, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        List<TimeShare> timeShareList = timeShareService.getTimeShareList(userId,startDate,endDate);
        return ResponseEntity.ok(timeShareList);
    }

    @GetMapping("/list/{taskId}")
    ResponseEntity<List<TimeShare>> getTimeShareLists(@PathVariable Long taskId){
        List<TimeShare> timeShareList = timeShareService.getTimeShareLists(taskId);
        return ResponseEntity.ok(timeShareList);
    }

    @GetMapping("/timeshare/maxId")
    ResponseEntity<Long> getMaxTimeShareId(){
        Long maxTimeShareId = timeShareService.getMaxTimeShareId();
        return ResponseEntity.ok(maxTimeShareId);
    }

    @PostMapping("/timeshare/{taskId}/{date}/{startTime}/{endTime}/{timeDifference}/{description}/{createdOn}")
    ResponseEntity<TimeShare> addTimeShare(@PathVariable Long taskId,@PathVariable LocalDateTime date,
                                           @PathVariable LocalTime startTime, @PathVariable LocalTime endTime,
                                           @PathVariable String timeDifference, @PathVariable String description,
                                           @PathVariable LocalTime createdOn,
                                           @RequestBody List<TimeShareMeasurables> timeShareMeasurablesList){
        TimeShare timeShare = timeShareService.addTimeShare(taskId,date,startTime,endTime,timeDifference,description,createdOn,timeShareMeasurablesList);
        return ResponseEntity.ok(timeShare);
    }



}
