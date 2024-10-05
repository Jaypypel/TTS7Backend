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

    @GetMapping("/dailyTimeShareList/{userId}/{dateOfTimeShare}")
    ResponseEntity<List<com.TTS.DbWebAPIs.Entity.DailyTimeShare>> getDailyTimeShareList(@PathVariable Long userId, @PathVariable LocalDate dateOfTimeShare){
        List<com.TTS.DbWebAPIs.Entity.DailyTimeShare> dailyTimeShares = dailyTimeShareService.getDailyTimeShareList(userId,dateOfTimeShare);
        return ResponseEntity.ok(dailyTimeShares);
    }

    @GetMapping("/User/{userId}/{startDate}/{endDate}/DTSReport")
    ResponseEntity<List<com.TTS.DbWebAPIs.Entity.DailyTimeShare>> getUserDTSReportDetails(@PathVariable Long userId, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        List<com.TTS.DbWebAPIs.Entity.DailyTimeShare> dailyTimeShares = dailyTimeShareService.getUserDTSReportDetails(userId,startDate,endDate);
        return ResponseEntity.ok(dailyTimeShares);
    }

    @GetMapping("/id/maximum")
    ResponseEntity<Integer> getDailyTimeShareMaxId(){
        Integer maxId = dailyTimeShareService.getMaxDailyTimeShareId();
        return ResponseEntity.ok(maxId);
    }

    @GetMapping("/Project/ConsumedTime/{userId}/{startDate}/{endDate}")
    ResponseEntity<List<String>> getProjectConsumedTime(@PathVariable Long userId, @PathVariable LocalDate startDate,@PathVariable LocalDate endDate){
        List<String> projectConsumedTime = dailyTimeShareService.getProjectConsumedTime(userId,startDate,endDate);
        return ResponseEntity.ok(projectConsumedTime);
    }


}