package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.OtherActivity;
import com.TTS.DbWebAPIs.Service.OtherActivityServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("OtherActivities")
public class OtherActivityController {

    private  final OtherActivityServiceInterface otherActivityService;

    @GetMapping("/list")
    ResponseEntity<List<OtherActivity>> getOtherActivityList() throws SQLException {
        List<OtherActivity> otherActivities = otherActivityService.getOtherActivityList() ;
        return ResponseEntity.ok(otherActivities);
    }

    @PostMapping("/activity/{otherActiName}/{createdOn}")
    ResponseEntity<OtherActivity> addOtherActivity(@PathVariable String  otherActiName, @PathVariable LocalTime createdOn){
        OtherActivity otherActivity = otherActivityService.addOtherActivity(otherActiName,createdOn);
        return ResponseEntity.ok(otherActivity);
    }
}
