package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.DTO.DailyTimeShareDTO;
import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.InternalServerException;
import com.TTS.DbWebAPIs.Exceptions.UserNotFoundException;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.DailyTimeShareServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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
    ResponseEntity<?> addDailyTimeShare(@RequestBody DailyTimeShare dailyTimeShare) throws UserNotFoundException, DatabaseException, InternalServerException {
        System.out.println("dailytime ob : " + dailyTimeShare );
        DailyTimeShare adddailyTimeShare = dailyTimeShareService.addDailyTimeShare(dailyTimeShare);
        return ResponseEntity.ok(new APIResponse<>("successful",adddailyTimeShare));
    }

    //tested at 11:17 am on 8th Oct
    @GetMapping("/dailyTimeShareList/{username}")
    ResponseEntity<?> getDailyTimeShareList(@PathVariable String username, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dateOfTimeShare) throws  DatabaseException, InternalServerException{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Parse the string to LocalDate
            LocalDate dateTimeShare = LocalDate.parse(dateOfTimeShare, formatter);
            List<DailyTimeShare> dailyTimeShares = dailyTimeShareService.getDailyTimeShareList(username,dateTimeShare);
            if (dailyTimeShares == null || dailyTimeShares.isEmpty()){
               return ResponseEntity
                       .status(HttpStatus.NO_CONTENT)
                       .body(new APIResponse<>("No dailyTimeShares found", null));
            }
           return ResponseEntity.ok(new APIResponse<>("successful",dailyTimeShares));

    }

    //tested at 12:21 pm on 8th Oct
    @GetMapping("/User/{userId}/{startDate}/{endDate}/DTSReport")
    ResponseEntity<?> getUserDTSReportDetails(@PathVariable String userId, @PathVariable String startDate, @PathVariable String endDate) throws DatabaseException,InternalServerException {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Parse the string to LocalDate
            LocalDate strtDate = LocalDate.parse(startDate, formatter);
            LocalDate edDate = LocalDate.parse(startDate, formatter);
            List<com.TTS.DbWebAPIs.Entity.DailyTimeShare> dailyTimeShares = dailyTimeShareService.getUserDTSReportDetails(userId,strtDate,edDate);
            if (dailyTimeShares == null || dailyTimeShares.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No dailyTimeShares found", null));
            }
            return ResponseEntity.ok(dailyTimeShares);
    }

    //tested at 12:49 pm on 8th Oct
    @GetMapping("/id/maximum")
    ResponseEntity<Long> getDailyTimeShareMaxId() throws SQLException {
        Long maxId = dailyTimeShareService.getMaxDailyTimeShareId();
        System.out.println(maxId);
        return ResponseEntity.ok(maxId);
    }

    //tested at 2:35 pm on 8th Oct
    @GetMapping("/Project/ConsumedTime/{username}/{startDate}/{endDate}")
    ResponseEntity<?> getProjectConsumedTime(@PathVariable String username, @PathVariable String startDate,@PathVariable String endDate) throws DatabaseException,InternalServerException {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Parse the string to LocalDate
            LocalDate strtDate = LocalDate.parse(startDate, formatter);
            LocalDate edDate = LocalDate.parse(startDate, formatter);
            List<String> projectConsumedTime = dailyTimeShareService.getProjectConsumedTime(username,strtDate,edDate);
            if (projectConsumedTime == null || projectConsumedTime.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No project consumed Time found", null));
            }
            return ResponseEntity.ok(projectConsumedTime);

    }

}