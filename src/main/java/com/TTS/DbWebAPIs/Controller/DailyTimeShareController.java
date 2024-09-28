package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import com.TTS.DbWebAPIs.Exceptions.AlreadyExistException;
import com.TTS.DbWebAPIs.Service.DailyTimeShareService;
import com.TTS.DbWebAPIs.Service.DailyTimeShareServiceInterface;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.bcel.LazyClassGen;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/dailyTimeShares")
@RequiredArgsConstructor
public class DailyTimeShareController {

    private final DailyTimeShareServiceInterface dailyTimeShareService;

    @PostMapping("/dailyTimeShare/")
    ResponseEntity<DailyTimeShare> addDailyTimeShare(@RequestBody DailyTimeShare dts, @RequestBody List<DailyTimeShareMeasurables> dailyTimeShareMeasurablesList) {
        DailyTimeShare dailyTimeShare = dailyTimeShareService.addDailyTimeShare(dts, dailyTimeShareMeasurablesList);
        return ResponseEntity.ok(dailyTimeShare);
    }

    @GetMapping("/dailyTimeShareList/{userId}/{dateOfTimeShare}")
    ResponseEntity<List<DailyTimeShare>> getDailyTimeShareList(@PathVariable Long userId, @PathVariable LocalDate dateOfTimeShare){
        List<DailyTimeShare> dailyTimeShares = dailyTimeShareService.getDailyTimeShareList(userId,dateOfTimeShare);
        return ResponseEntity.ok(dailyTimeShares);
    }

    @GetMapping("/User/{userId}/{startDate}/{endDate}/DTSReport")
    ResponseEntity<List<DailyTimeShare>> getUserDTSReportDetails(@PathVariable Long userId, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        List<DailyTimeShare> dailyTimeShares = dailyTimeShareService.getUserDTSReportDetails(userId,startDate,endDate);
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