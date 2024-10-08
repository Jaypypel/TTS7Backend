package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.DTO.DailyTimeShareDTO;
import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import com.TTS.DbWebAPIs.Service.DailyTimeShareServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/dailyTimeShares")
@RequiredArgsConstructor
public class DailyTimeShareController {

    private final DailyTimeShareServiceInterface dailyTimeShareService;

    //tested at 1:06 pm on 4th oct
    @PostMapping("/dailyTimeShare/")
    ResponseEntity<com.TTS.DbWebAPIs.Entity.DailyTimeShare> addDailyTimeShare(@RequestBody DailyTimeShareDTO dailyTimeShareDTO) {
        com.TTS.DbWebAPIs.Entity.DailyTimeShare dailyTimeShare = dailyTimeShareDTO.getDailyTimeShare();
        List<DailyTimeShareMeasurables> dailyTimeShareMeasurables = dailyTimeShareDTO.getDailyTimeShareMeasurables();
        com.TTS.DbWebAPIs.Entity.DailyTimeShare adddailyTimeShare = dailyTimeShareService.addDailyTimeShare(dailyTimeShare,dailyTimeShareMeasurables);
        return ResponseEntity.ok(adddailyTimeShare);
    }

    //tested at 11:17 am on 8th Oct
    @GetMapping("/dailyTimeShareList/{username}/{dateOfTimeShare}")
    ResponseEntity<List<com.TTS.DbWebAPIs.Entity.DailyTimeShare>> getDailyTimeShareList(@PathVariable String username, @PathVariable LocalDate dateOfTimeShare){
        List<com.TTS.DbWebAPIs.Entity.DailyTimeShare> dailyTimeShares = dailyTimeShareService.getDailyTimeShareList(username,dateOfTimeShare);
        return ResponseEntity.ok(dailyTimeShares);
    }

    //tested at 12:21 pm on 8th Oct
    @GetMapping("/User/{userId}/{startDate}/{endDate}/DTSReport")
    ResponseEntity<List<com.TTS.DbWebAPIs.Entity.DailyTimeShare>> getUserDTSReportDetails(@PathVariable String userId, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
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