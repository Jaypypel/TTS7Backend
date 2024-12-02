package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.TimeShareMeasurables;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.TimeShareMeasurablesServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        TimeShareMeasurables timeShareMeasurables = timeShareMeasurablesService.addTimeShareMeasurables(
                timeShareId,measuableId,measurableQuantity,measurableUnit);
        return ResponseEntity.ok(new APIResponse("successful",timeShareMeasurables));
    }



}
