package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.MeasurablesIdAndName;
import com.TTS.DbWebAPIs.Service.DailyTimeShareMeasurablesServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("DailyTimeShareMeasurables")
@RequiredArgsConstructor
public class DailyTimeShareMeasurablesController {

    private final DailyTimeShareMeasurablesServiceInterface dailyTimeShareMeasurablesService;

    @GetMapping("/dailyTimeShareMeasurable/{timeShareId}/{measurablesId}/{mesrbQunty}/{mesrbUnit}")
    ResponseEntity<DailyTimeShareMeasurables> addDailyTimeShareMeasurables(@PathVariable TimeShare timeShareId, @PathVariable Measurables measurablesId,
                                                                           @PathVariable Long mesrbQunty,@PathVariable String mesrbUnit){
        DailyTimeShareMeasurables dailyTimeShareMeasurables = dailyTimeShareMeasurablesService.addDailyTimeShareMeasurables(timeShareId,measurablesId,mesrbQunty,mesrbUnit);
        return ResponseEntity.ok(dailyTimeShareMeasurables);
    }

    @GetMapping("/dailyTimeShareList/{dtsId}")
    ResponseEntity<List<Measurables>> getDailyTimeMeasurablesList(@PathVariable Long dtsId){
        List<Measurables> dailyTimeShareMeasurables = dailyTimeShareMeasurablesService.getDailyTimeShareMeasurablesList(dtsId);
        return ResponseEntity.ok(dailyTimeShareMeasurables);
     }
}
