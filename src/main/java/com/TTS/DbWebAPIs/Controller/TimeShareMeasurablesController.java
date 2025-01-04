package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.TimeShareMeasurables;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.TimeShareMeasurablesServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("timesharemeasurables")
public class TimeShareMeasurablesController {

    private final TimeShareMeasurablesServiceInterface timeShareMeasurablesService;

    @PostMapping("/add/timeharemeasurable")
    ResponseEntity<?> addTimeShareMeasurables(@RequestParam Long timeShareId,
                                                                 @RequestParam  Measurables measuableId,
                                                                 @RequestParam Long measurableQuantity,
                                                                 @RequestParam String measurableUnit){
       try{
           TimeShareMeasurables timeShareMeasurables = timeShareMeasurablesService.addTimeShareMeasurables(
                   timeShareId,measuableId,measurableQuantity,measurableUnit);
           return ResponseEntity.ok(new APIResponse("successful",timeShareMeasurables));
       }catch (SQLException ex){
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new APIResponse<>("An error occured while adding a time share. Please try again later.",null));
       } catch (Exception ex){
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
       }
    }



}
