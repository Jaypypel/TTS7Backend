package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.MeasurablesIdAndName;
import com.TTS.DbWebAPIs.Repository.MeasurablesRepository;
import com.TTS.DbWebAPIs.Service.MeasurablesService;
import com.TTS.DbWebAPIs.Service.MeasurablesServiceInterface;
import lombok.RequiredArgsConstructor;

import org.hibernate.boot.jaxb.internal.stax.LocalSchemaLocator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("Measurables")
@RequiredArgsConstructor
public class MeasurablesController {

    private  final MeasurablesServiceInterface measurablesService;

    @GetMapping("/DTSMeasurablesList/{dtsId}")
    ResponseEntity<List<Measurables>> getDTSMeasurablesList(@PathVariable Long dtsId){
        List<Measurables> measurables = measurablesService.getDTSMeasurablesList(dtsId);
        return ResponseEntity.ok(measurables);
    }

    @GetMapping("/list")
    ResponseEntity<List<Measurables>> getMeasurableList(){
        List<Measurables> measurables = measurablesService.getMeasurableList();
        return ResponseEntity.ok(measurables);
    }

    @GetMapping("/count/{userId}/{startDate}/{endDate}/")
    ResponseEntity<Integer> getMeasurablesCount(@PathVariable Long userId,@PathVariable LocalDate startDate,@PathVariable LocalDate endDate){
        Integer measurablesCount = measurablesService.getMeasurableCount(userId,startDate,endDate);
        return ResponseEntity.ok(measurablesCount);
    }

    @GetMapping("/{userId}/list")
    ResponseEntity<List<Measurables>> getMeasurablesListForUserId(@PathVariable Long userId){
        List<Measurables> measurables = measurablesService.getMeasurableListForUserID(userId);
        return ResponseEntity.ok(measurables);
    }

    @PostMapping("/measurable/{userId}/{measurableName}/{createdOn}")
    ResponseEntity<Measurables> addMeasurable(@PathVariable Long userId, @PathVariable String measurableName, @PathVariable LocalTime createdOn){
        Measurables measurables = measurablesService.addMeasurable(userId,measurableName,createdOn);
        return ResponseEntity.ok(measurables);
    }


}
