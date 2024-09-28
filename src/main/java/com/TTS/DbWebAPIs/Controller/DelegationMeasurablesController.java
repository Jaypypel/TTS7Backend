package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.DelegationMeasurables;
import com.TTS.DbWebAPIs.Service.DelegationMeasurablesServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class DelegationMeasurablesController {

    private final DelegationMeasurablesServiceInterface delegationMeasurablesService;

    @GetMapping("/allocatedMeasurabeslist/{taskId}")
    ResponseEntity<List<DelegationMeasurables>> getAllocatedMeasurableList(Long taskId){
       List<DelegationMeasurables> delegationMeasurables = delegationMeasurablesService.getAllocatedMeasurableList(taskId);
       return ResponseEntity.ok(delegationMeasurables);
    }

}
