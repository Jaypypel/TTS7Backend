package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.DTO.MeasurablesDTO;
import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.InternalServerException;
import com.TTS.DbWebAPIs.Exceptions.UserNotFoundException;
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

import java.sql.SQLException;
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
    ResponseEntity<?> getDTSMeasurablesList(@PathVariable Long dtsId) throws DatabaseException  , InternalServerException{

            List<Measurables> measurables = measurablesService.getDTSMeasurablesList(dtsId);
            if (measurables == null || measurables.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No measurable found", null));
            }
            return ResponseEntity.ok(new APIResponse<>("successful",MeasurablesDTO.convertToMeasurableDTO(measurables)));

    }

    //tested at 2:30 on 4th oct
    @GetMapping("/list")
    ResponseEntity<APIResponse> getMeasurableList() throws DatabaseException  , InternalServerException{

            List<Measurables> measurables = measurablesService.getMeasurableList();
            if (measurables == null || measurables.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No measurable found", null));
            }
            return ResponseEntity.ok(new APIResponse<>("measurable list",MeasurablesDTO.convertToMeasurableDTO(measurables)));

    }


    @GetMapping("/measurable-list/")
    ResponseEntity<?> getMeasurables() throws DatabaseException , InternalServerException {

            List<Measurables> measurables = measurablesService.getMeasurableList();
            if (measurables == null || measurables.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("");
            }
            return ResponseEntity.ok(new APIResponse<>("measurable list",MeasurablesDTO.convertToMeasurableDTO(measurables)));

    }

    @GetMapping("/count/{username}/{startDate}/{endDate}/")
    ResponseEntity<?> getMeasurablesCount(@PathVariable String username, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @PathVariable LocalDate endDate) throws DatabaseException , InternalServerException {

            Integer measurablesCount = measurablesService.getMeasurableCount(username,startDate,endDate);
            if (measurablesCount == null || measurablesCount==0){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("0", null));
            }
            return ResponseEntity.ok(measurablesCount);

    }

    //tested on 7 oct at 4:41 pm
    @GetMapping("/names")
    ResponseEntity<?> getMeasurableNamesbyUsername(@RequestParam String username) throws DatabaseException, InternalServerException {
            List<String> measurables = measurablesService.getMeasurableListForUsername(username);
            if (measurables == null || measurables.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No measurable found", null));
            }
            return ResponseEntity.ok(new APIResponse<>("successful",measurables));

    }

    //tested on 7 oct at 4:15pm
    @PostMapping("/measurable")
    ResponseEntity<?> addMeasurable(
            @RequestParam String username, @RequestParam String measurableName, @RequestParam String createdOn)
            throws DatabaseException, UserNotFoundException, InternalServerException {

           Measurables measurables = measurablesService.addMeasurable(username,measurableName,createdOn);
           return ResponseEntity.ok(new APIResponse<>("successful","-"));

    }


}
