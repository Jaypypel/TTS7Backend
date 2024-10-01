package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.TimeShareMeasurables;
import com.TTS.DbWebAPIs.Service.TimeShareMeasurablesServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("timesharemeasurables")
public class TimeShareMeasurablesController {

    private final TimeShareMeasurablesServiceInterface timeShareMeasurablesService;

    @PostMapping("/add/timeharemeasurable/{timeShareId}/{measurableQuantity}/{measurableUnit}")
    ResponseEntity<TimeShareMeasurables> addTimeShareMeasurables(@PathVariable Long timeShareId,
                                                                 @RequestBody  Measurables measuableId,
                                                                 @PathVariable Long measurableQuantity,
                                                                 @PathVariable String measurableUnit){
        TimeShareMeasurables timeShareMeasurables = timeShareMeasurablesService.addTimeShareMeasurables(
                timeShareId,measuableId,measurableQuantity,measurableUnit);
        return ResponseEntity.ok(timeShareMeasurables);
    }
}
