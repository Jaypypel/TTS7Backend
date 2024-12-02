package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.DTO.DailyTimeShareDTO;
import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.DailyTimeShareServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/dailyTimeShares")
@RequiredArgsConstructor
public class DailyTimeShareController {

    private final DailyTimeShareServiceInterface dailyTimeShareService;

    //tested at 1:06 pm on 4th oct
    @PostMapping("/dailyTimeShare/")
    ResponseEntity<APIResponse> addDailyTimeShare(@RequestBody DailyTimeShare dailyTimeShare) {
//        System.out.println(dailyTimeShareDTO);
//        DailyTimeShare dailyTimeShare = dailyTimeShareDTO.getDailyTimeShare();
//        System.out.println(dailyTimeShare);
//        List<DailyTimeShareMeasurables> dailyTimeShareMeasurables = dailyTimeShareDTO.getDailyTimeShareMeasurables();
//        System.out.println(dailyTimeShareMeasurables);
        if(dailyTimeShare ==null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new APIResponse("getting dailytimeshare nul", null));
        }
        System.out.println("User from front end : "+dailyTimeShare.getUser());
        System.out.println("dailyTime from frontend: " + dailyTimeShare);
        DailyTimeShare adddailyTimeShare = dailyTimeShareService.addDailyTimeShare(dailyTimeShare);
        System.out.println(adddailyTimeShare);
        return ResponseEntity.ok(new APIResponse("successful",adddailyTimeShare));
    }

    //tested at 11:17 am on 8th Oct
    @GetMapping("/dailyTimeShareList/{username}/{dateOfTimeShare}")
    ResponseEntity<APIResponse> getDailyTimeShareList(@PathVariable String username, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateOfTimeShare){
        if( username == null || dateOfTimeShare == null ){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new APIResponse("failed",null));
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate TimeShareDate = LocalDate.parse(dateOfTimeShare,formatter)    ;
        List<DailyTimeShare> dailyTimeShares = dailyTimeShareService.getDailyTimeShareList(username,TimeShareDate);
        return ResponseEntity.ok(new APIResponse("successful",dailyTimeShares));
    }

    //tested at 12:21 pm on 8th Oct
    @GetMapping("/User/{userId}/{startDate}/{endDate}/DTSReport")
    ResponseEntity<List<DailyTimeShare>> getUserDTSReportDetails(@PathVariable String userId, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        List<com.TTS.DbWebAPIs.Entity.DailyTimeShare> dailyTimeShares = dailyTimeShareService.getUserDTSReportDetails(userId,startDate,endDate);
        return ResponseEntity.ok(dailyTimeShares);
    }

    //tested at 12:49 pm on 8th Oct
    @GetMapping("/id/maximum")
    ResponseEntity<Long> getDailyTimeShareMaxId(){
        Long maxId = dailyTimeShareService.getMaxDailyTimeShareId();
        System.out.println(maxId);
        return ResponseEntity.ok(maxId);
    }

    //tested at 2:35 pm on 8th Oct
    @GetMapping("/Project/ConsumedTime/{username}/{startDate}/{endDate}")
    ResponseEntity<List<String>> getProjectConsumedTime(@PathVariable String username, @PathVariable LocalDate startDate,@PathVariable LocalDate endDate){
        System.out.println("username : "+username);
        System.out.println("startdate : "+startDate);
        System.out.println("enddate : "+endDate);
        List<String> projectConsumedTime = dailyTimeShareService.getProjectConsumedTime(username,startDate,endDate);
        System.out.println(projectConsumedTime);
        return ResponseEntity.ok(projectConsumedTime);}

}