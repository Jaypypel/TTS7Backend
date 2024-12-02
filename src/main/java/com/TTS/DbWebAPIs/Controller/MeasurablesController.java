package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.DTO.MeasurablesDTO;
import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.MeasurablesIdAndName;
import com.TTS.DbWebAPIs.Repository.MeasurablesRepository;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.MeasurablesService;
import com.TTS.DbWebAPIs.Service.MeasurablesServiceInterface;
import lombok.RequiredArgsConstructor;

import org.hibernate.boot.jaxb.internal.stax.LocalSchemaLocator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("Measurables")
@RequiredArgsConstructor
public class MeasurablesController {

    private  final MeasurablesServiceInterface measurablesService;

    //tested at 2:15 on 4th oct
    @GetMapping("/DTSMeasurablesList/{dtsId}")
    ResponseEntity<APIResponse> getDTSMeasurablesList(@PathVariable Long dtsId){
        List<Measurables> measurables = measurablesService.getDTSMeasurablesList(dtsId);
        return ResponseEntity.ok(new APIResponse("successful",measurables));
    }

    //tested at 2:30 on 4th oct
    @GetMapping("/list")
    ResponseEntity<APIResponse> getMeasurableList(){
        List<Measurables> measurables = measurablesService.getMeasurableList();
        ArrayList<MeasurablesDTO> measurableList = new ArrayList<>();
        MeasurablesDTO measurable;
        for(Measurables m: measurables){
            measurable = new MeasurablesDTO(m.getId(),m.getName());
            measurableList.add(measurable);

        }

        return ResponseEntity.ok(new APIResponse("measurable list",measurableList));
    }

    @GetMapping("/count/{username}/{startDate}/{endDate}/")
    ResponseEntity<Integer> getMeasurablesCount(@PathVariable String username, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @PathVariable LocalDate endDate){
        Integer measurablesCount = measurablesService.getMeasurableCount(username,startDate,endDate);
        return ResponseEntity.ok(measurablesCount);
    }

    //tested on 7 oct at 4:41 pm
    @GetMapping("/names")
    ResponseEntity<?> getMeasurableNamesbyUsername(@RequestParam String username){
        try {
            List<String> measurables = measurablesService.getMeasurableListForUsername(username);
            System.out.println(measurables);
            return ResponseEntity.ok(new APIResponse("successful",measurables));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    //tested on 7 oct at 4:15pm
    @PostMapping("/measurable")
    ResponseEntity<?> addMeasurable(@RequestParam String username, @RequestParam String measurableName, @RequestParam String createdOn){
       try{
           Measurables measurables = measurablesService.addMeasurable(username,measurableName,createdOn);
           return ResponseEntity.ok(new APIResponse("successful","-"));
       }catch (RuntimeException e){
           return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
       }
    }


}
