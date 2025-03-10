package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.TimeShareMeasurables;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.InternalServerException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.TimeShareMeasurablesServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("timesharemeasurables1")
public class TimeShareMeasurablesController {

    private final TimeShareMeasurablesServiceInterface timeShareMeasurablesService;

    @PostMapping("/add/timeharemeasurable")
    ResponseEntity<?> addTimeShareMeasurables(@RequestParam Long timeShareId,
                                                                 @RequestParam  Measurables measuableId,
                                                                 @RequestParam Long measurableQuantity,
                                                                 @RequestParam String measurableUnit)
            throws NotFoundException, DatabaseException, InternalServerException {
           TimeShareMeasurables timeShareMeasurables = timeShareMeasurablesService
                   .addTimeShareMeasurables(timeShareId,measuableId,measurableQuantity,measurableUnit);
           return ResponseEntity
                   .ok(new APIResponse<>("successful",timeShareMeasurables));

    }



}
