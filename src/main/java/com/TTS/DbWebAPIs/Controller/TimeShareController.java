package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.DTO.TimeShareDTO;
import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Entity.TimeShareMeasurables;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.InternalServerException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Exceptions.UserNotFoundException;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.TimeShareServiceInterface;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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
    ResponseEntity<?> getTimeShareList
    (@PathVariable String username, @PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate)
            throws DatabaseException, InternalServerException, UserNotFoundException {
            List<TimeShare> timeShareList = timeShareService.getTimeShareList(username,startDate,endDate);
            if (timeShareList == null || timeShareList.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No time share found", null));
            }
            return ResponseEntity.ok(new APIResponse<>("successful",timeShareList));
    }

    //tested at 12:58 pm on 10 oct
    @GetMapping("/list/{taskId}")
    ResponseEntity<?> getTimeShareLists(@PathVariable Long taskId) throws DatabaseException, InternalServerException, NotFoundException {
            List<TimeShare> timeShareList = timeShareService.getTimeShareLists(taskId);
            if (timeShareList == null || timeShareList.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No time share found", null));
            }
            return ResponseEntity.ok(new APIResponse<>("successful",timeShareList));
    }

    //tested at 1:35 on 10 oct
    @GetMapping("/timeshare/maxId")
    ResponseEntity<Long> getMaxTimeShareId() throws DatabaseException {
        Long maxTimeShareId = timeShareService.getMaxTimeShareId();
        return ResponseEntity.ok(maxTimeShareId);

    }

    //tested at 12:27 pm on 4th oct 2024
    @PostMapping("/timeshare")
    ResponseEntity<?> addTimeShare(@RequestBody TimeShareDTO timeShareDTO) throws DatabaseException, InternalServerException {
        System.out.println("date from input"+timeShareDTO.getDate());
             System.out.println("Dto"+timeShareDTO);
            TimeShare timeShare = timeShareService.addTimeShare(TimeShareDTO.convertToTimeShare(timeShareDTO));
        System.out.println("timeshare"+timeShare);
        return ResponseEntity.ok(new APIResponse<>("successful",timeShare));
    }



}
