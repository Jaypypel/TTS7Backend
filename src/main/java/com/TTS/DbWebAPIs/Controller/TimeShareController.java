package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.DTO.TimeShareDTO;
import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Entity.TimeShareMeasurables;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.TimeShareServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    //tested at 12:54 pm on 10 oct
    @GetMapping("/list/{username}/{startDate}/{endDate}")
    ResponseEntity<List<TimeShare>> getTimeShareList(@PathVariable String username, @PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate){
        List<TimeShare> timeShareList = timeShareService.getTimeShareList(username,startDate,endDate);
        return ResponseEntity.ok(timeShareList);
    }

    //tested at 12:58 pm on 10 oct
    @GetMapping("/list/{taskId}")
    ResponseEntity<?> getTimeShareLists(@PathVariable Long taskId){
        try{
            List<TimeShare> timeShareList = timeShareService.getTimeShareLists(taskId);
            return ResponseEntity.ok(new APIResponse("successful",timeShareList));
        }catch (RuntimeException e){

            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    //tested at 1:35 on 10 oct
    @GetMapping("/timeshare/maxId")
    ResponseEntity<Long> getMaxTimeShareId(){
        Long maxTimeShareId = timeShareService.getMaxTimeShareId();
        return ResponseEntity.ok(maxTimeShareId);
    }

    //tested at 12:27 pm on 4th oct 2024
    @PostMapping("/timeshare")
    ResponseEntity<?> addTimeShare(@RequestBody TimeShareDTO timeShareDTO){

        TimeShare timeShare = timeShareService.addTimeShare(TimeShareDTO.convertToTimeShare(timeShareDTO));
        return ResponseEntity.ok(new APIResponse("successful",timeShare));
    }



}
