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
    ResponseEntity<List<Measurables>> getDTSMeasurablesList(@PathVariable Long dtsId){
        List<Measurables> measurables = measurablesService.getDTSMeasurablesList(dtsId);
        return ResponseEntity.ok(measurables);
    }

    //tested at 2:30 on 4th oct
    @GetMapping("/list")
    ResponseEntity<APIResponse> getMeasurableList(){
        List<Measurables> measurables = measurablesService.getMeasurableList();
        ArrayList<MeasurablesDTO> measurable = new ArrayList<>();
        for(Measurables m: measurables){
            MeasurablesDTO measurablesDTO = new MeasurablesDTO(m.getName(),m.getId());
            measurable.add(measurablesDTO);

        }

        return ResponseEntity.ok(new APIResponse("measurable list",measurable));
    }

    @GetMapping("/count/{username}/{startDate}/{endDate}/")
    ResponseEntity<Integer> getMeasurablesCount(@PathVariable String username,@PathVariable LocalDate startDate,@PathVariable LocalDate endDate){
        Integer measurablesCount = measurablesService.getMeasurableCount(username,startDate,endDate);
        return ResponseEntity.ok(measurablesCount);
    }

    //tested on 7 oct at 4:41 pm
    @GetMapping("/{username}/list")
    ResponseEntity<List<Measurables>> getMeasurablesListForUserId(@PathVariable String username){
        List<Measurables> measurables = measurablesService.getMeasurableListForUsername(username);
        System.out.println(measurables);
        return ResponseEntity.ok(measurables);
    }

    //tested on 7 oct at 4:15pm
    @PostMapping("/measurable/{username}/{measurableName}/{createdOn}")
    ResponseEntity<Measurables> addMeasurable(@PathVariable String username, @PathVariable String measurableName, @PathVariable LocalTime createdOn){
        Measurables measurables = measurablesService.addMeasurable(username,measurableName,createdOn);
        return ResponseEntity.ok(measurables);
    }


}
