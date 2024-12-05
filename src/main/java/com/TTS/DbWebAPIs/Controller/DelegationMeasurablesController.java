package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.*;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.DelegationMeasurablesServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("delegationMeasurables")
public class DelegationMeasurablesController {

    private final DelegationMeasurablesServiceInterface delegationMeasurablesService;

    @GetMapping("/allocatedMeasurabeslist/{taskId}")
    ResponseEntity<APIResponse> getAllocatedMeasurableList(@PathVariable Long taskId){
       List<Measurables> delegationMeasurables = delegationMeasurablesService.getAllocatedMeasurableList(taskId);
       System.out.println(delegationMeasurables);
       return ResponseEntity.ok(new APIResponse("successful",delegationMeasurables));
    }

    @PostMapping("/add-delegationMeasurable")
    ResponseEntity<APIResponse> addDailyTimeShareMeasurable(@RequestParam Long taskHandlerId, @RequestParam Long measurablesId,
                                                            @RequestParam Long mesrbQunty, @RequestParam String mesrbUnit){
        System.out.println("ts_id  :"+taskHandlerId );
        System.out.println("m_id  :"+measurablesId );
        System.out.println("mesrbQunty  :"+mesrbQunty );
        System.out.println("mesrbUnit  :"+mesrbUnit );

        if(taskHandlerId == null || measurablesId == null || mesrbQunty == null  || mesrbUnit==null){
            System.out.println("Any of field like measurId, timesharId is not null");
        }
        TaskManagement taskManagement = new TaskManagement();
        taskManagement.setId(taskHandlerId);

        Measurables measurables = new Measurables();
        measurables.setId(measurablesId);
        DelegationMeasurables delegationMeasurable = delegationMeasurablesService.addDelegationMeasurables(taskManagement,measurables,mesrbQunty,mesrbUnit);
        System.out.println(delegationMeasurable);
        return ResponseEntity.ok(new APIResponse("successful",delegationMeasurable));
    }

}
