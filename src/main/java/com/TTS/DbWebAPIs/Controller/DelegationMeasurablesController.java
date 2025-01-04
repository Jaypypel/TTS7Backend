package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.*;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Repository.MeasurablesRepository;
import com.TTS.DbWebAPIs.Repository.TaskManagementRepository;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.DelegationMeasurablesServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("delegationMeasurables")
public class DelegationMeasurablesController {

    private final DelegationMeasurablesServiceInterface delegationMeasurablesService;
    private final TaskManagementRepository taskManagementRepository;
    private final MeasurablesRepository measurablesRepository;

    @GetMapping("/allocatedMeasurabeslist/{taskId}")
    ResponseEntity<APIResponse> getAllocatedMeasurableList(@PathVariable Long taskId){

        try {
            List<Measurables> delegationMeasurables = delegationMeasurablesService.getAllocatedMeasurableList(taskId);
            if (delegationMeasurables == null || delegationMeasurables.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No measurable found", null));
            }
            return ResponseEntity.ok(new APIResponse<>("successful",delegationMeasurables));
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

    @PostMapping("/add-delegationMeasurable")
    ResponseEntity<?> addDailyTimeShareMeasurable(@RequestParam Long taskHandlerId, @RequestParam Long measurablesId,
                                                            @RequestParam Long mesrbQunty, @RequestParam String mesrbUnit){

        try {
            TaskManagement taskManagement = taskManagementRepository.findById(taskHandlerId).orElseThrow(()
                    -> new  NotFoundException("task Management not found"));
            Measurables measurables =  measurablesRepository.findById(measurablesId).orElseThrow(()
                    -> new  NotFoundException("measurable not found"));
            DelegationMeasurables delegationMeasurable = delegationMeasurablesService.
                    addDelegationMeasurables(taskManagement,measurables,mesrbQunty,mesrbUnit);
            return ResponseEntity.ok(new APIResponse("successful",delegationMeasurable));
        }catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while fetching adding measurables of daily time share. " +
                            "Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

}
