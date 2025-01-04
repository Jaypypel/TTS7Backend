package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.MeasurablesIdAndName;
import com.TTS.DbWebAPIs.Repository.MeasurablesRepository;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.DailyTimeShareMeasurablesServiceInterface;
import com.TTS.DbWebAPIs.Service.MeasurablesServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/DailyTimeShareMeasurables")
@RequiredArgsConstructor
public class DailyTimeShareMeasurablesController {

    private final DailyTimeShareMeasurablesServiceInterface dailyTimeShareMeasurablesService;
    private final MeasurablesRepository measurablesRepository;



    @PostMapping("/dailyTimeShareMeasurable")
    ResponseEntity<?> addDailyTimeShareMeasurable(@RequestParam Long timeShareId, @RequestParam Long measurablesId,
                                                            @RequestParam Long mesrbQunty, @RequestParam String mesrbUnit){
       try {
           Measurables measurable = measurablesRepository.findById(measurablesId)
                   .orElseThrow(() -> new NotFoundException("Measurable not found"));
           DailyTimeShareMeasurables dailyTimeShareMeasurables = dailyTimeShareMeasurablesService.addDailyTimeShareMeasurables(timeShareId,measurable,mesrbQunty,mesrbUnit);
           return ResponseEntity.ok(new APIResponse<>("successful",dailyTimeShareMeasurables));
       }
        catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while getting  dailyTimeshares. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    @GetMapping("/dailyTimeShareList/{dtsId}")
    ResponseEntity<?> getDailyTimeMeasurablesList(@PathVariable Long dtsId){
        try {
            List<Measurables> dailyTimeShareMeasurables = dailyTimeShareMeasurablesService
                    .getDailyTimeShareMeasurablesList(dtsId);
            if (dailyTimeShareMeasurables == null || dailyTimeShareMeasurables.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No measurables found", null));
            }
            return ResponseEntity.ok(dailyTimeShareMeasurables);
        }catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while getting  dailyTimeshares. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
     }
}
