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
    ResponseEntity<?> getTimeShareList(@PathVariable String username, @PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate){
        try {
            List<TimeShare> timeShareList = timeShareService.getTimeShareList(username,startDate,endDate);

            if (timeShareList == null || timeShareList.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No time share found", null));
            }
            return ResponseEntity.ok(new APIResponse<>("succesful",timeShareList));
        }catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occured while fetching time share list. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    //tested at 12:58 pm on 10 oct
    @GetMapping("/list/{taskId}")
    ResponseEntity<?> getTimeShareLists(@PathVariable Long taskId){
        try{
            List<TimeShare> timeShareList = timeShareService.getTimeShareLists(taskId);
            if (timeShareList == null || timeShareList.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No time share found", null));
            }
            return ResponseEntity.ok(new APIResponse<>("successful",timeShareList));
        }catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occured while fetching time share list. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
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

        try {
            TimeShare timeShare = timeShareService.addTimeShare(TimeShareDTO.convertToTimeShare(timeShareDTO));
            return ResponseEntity.ok(new APIResponse<>("successful",timeShare));
        }
         catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occured while adding a time share list. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }



}
