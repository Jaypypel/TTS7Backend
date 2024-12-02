package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.MeasurablesIdAndName;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.DailyTimeShareMeasurablesServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DailyTimeShareMeasurables")
@RequiredArgsConstructor
public class DailyTimeShareMeasurablesController {

    private final DailyTimeShareMeasurablesServiceInterface dailyTimeShareMeasurablesService;

    @PostMapping("/dailyTimeShareMeasurable")
    ResponseEntity<APIResponse> addDailyTimeShareMeasurable(@RequestParam Long timeShareId, @RequestParam Long measurablesId,
                                                            @RequestParam Long mesrbQunty, @RequestParam String mesrbUnit){
        System.out.println("ts_id  :"+timeShareId );
        System.out.println("m_id  :"+measurablesId );
        System.out.println("mesrbQunty  :"+mesrbQunty );
        System.out.println("mesrbUnit  :"+mesrbUnit );

        if(timeShareId == null || measurablesId == null || mesrbQunty == null  || mesrbUnit==null){
            System.out.println("Any of field like measurId, timesharId is not null");
        }
        TimeShare timeShare = new TimeShare();
        timeShare.setId(timeShareId);
        Measurables measurables = new Measurables();
        measurables.setId(measurablesId);
        DailyTimeShareMeasurables dailyTimeShareMeasurables = dailyTimeShareMeasurablesService.addDailyTimeShareMeasurables(timeShare,measurables,mesrbQunty,mesrbUnit);
        System.out.println(dailyTimeShareMeasurables);
        return ResponseEntity.ok(new APIResponse("successful",dailyTimeShareMeasurables));
    }

    @GetMapping("/dailyTimeShareList/{dtsId}")
    ResponseEntity<List<Measurables>> getDailyTimeMeasurablesList(@PathVariable Long dtsId){
        List<Measurables> dailyTimeShareMeasurables = dailyTimeShareMeasurablesService.getDailyTimeShareMeasurablesList(dtsId);
        return ResponseEntity.ok(dailyTimeShareMeasurables);
     }
}
