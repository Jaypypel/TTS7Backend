package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.MeasurableUnit;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Service.MeasurableUnitServiceInterface;
import jakarta.persistence.GenerationType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("measurables1")
public class MeasurableUnitController {
    private final MeasurableUnitServiceInterface measurableUnitService;

    @GetMapping("/unitlist")
    ResponseEntity<List<MeasurableUnit>> getMeasurableUnit() throws DatabaseException {
       List<MeasurableUnit> measurableUnitList = measurableUnitService.getMeasurableUnitList();
       return ResponseEntity.ok(measurableUnitList);
    }
}
