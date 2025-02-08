package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.DTO.MeasurablesDTO;
import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
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
    ResponseEntity<?> getDTSMeasurablesList(@PathVariable Long dtsId){
        try {
            List<Measurables> measurables = measurablesService.getDTSMeasurablesList(dtsId);
            if (measurables == null || measurables.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No measurable found", null));
            }
            return ResponseEntity.ok(new APIResponse<>("successful",MeasurablesDTO.convertToMeasurableDTO(measurables)));
        }catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while fetching getting measurable/s. " +
                            "Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    //tested at 2:30 on 4th oct
    @GetMapping("/list")
    ResponseEntity<APIResponse> getMeasurableList(){
        try {
            List<Measurables> measurables = measurablesService.getMeasurableList();
            if (measurables == null || measurables.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No measurable found", null));
            }
            return ResponseEntity.ok(new APIResponse<>("measurable list",MeasurablesDTO.convertToMeasurableDTO(measurables)));
        } catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while getting measurable/s. " +
                            "Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }


    @GetMapping("/measurable-list/")
    ResponseEntity<?> getMeasurables(){
        try {
            List<Measurables> measurables = measurablesService.getMeasurableList();
            if (measurables == null || measurables.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body("");
            }
            return ResponseEntity.ok(new APIResponse<>("measurable list",MeasurablesDTO.convertToMeasurableDTO(measurables)));
        } catch (SQLException ex){
          throw new DatabaseException("Error accessing measurable/s from database", ex);
        } catch (Exception ex){
            throw  new RuntimeException(ex.getMessage());
        }
    }

    @GetMapping("/count/{username}/{startDate}/{endDate}/")
    ResponseEntity<?> getMeasurablesCount(@PathVariable String username, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @PathVariable LocalDate endDate){
        try {
            Integer measurablesCount = measurablesService.getMeasurableCount(username,startDate,endDate);
            if (measurablesCount == null || measurablesCount==0){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("0", null));
            }
            return ResponseEntity.ok(measurablesCount);
        }
         catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while getting measurable/s. " +
                            "Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    //tested on 7 oct at 4:41 pm
    @GetMapping("/names")
    ResponseEntity<?> getMeasurableNamesbyUsername(@RequestParam String username){
        try {
            List<String> measurables = measurablesService.getMeasurableListForUsername(username);
            if (measurables == null || measurables.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No measurable found", null));
            }
            return ResponseEntity.ok(new APIResponse("successful",measurables));
        } catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while getting measurable/s. " +
                            "Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    //tested on 7 oct at 4:15pm
    @PostMapping("/measurable")
    ResponseEntity<?> addMeasurable(@RequestParam String username, @RequestParam String measurableName, @RequestParam String createdOn){
       try{
           Measurables measurables = measurablesService.addMeasurable(username,measurableName,createdOn);
           return ResponseEntity.ok(new APIResponse("successful","-"));
       } catch (SQLException ex){
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new APIResponse<>("An error occurred while adding a measurable. " +
                           "Please try again later.",null));
       } catch (Exception ex){
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
       }
    }


}
