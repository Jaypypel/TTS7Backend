package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.OtherActivity;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.OtherActivityServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
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

    //tested at 10:17 on 8 Oct
    @GetMapping("/list")
    ResponseEntity<?> getOtherActivityList() throws SQLException {
      try {
          List<String> otherActivities = otherActivityService.getOtherActivityList() ;
          System.out.println(otherActivities );
          return ResponseEntity.ok(new APIResponse("successful",otherActivities));
      } catch (RuntimeException e){
          return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
      }
    }

    //tested at 10:00  on 8 Oct
    @PostMapping("/activity")
    ResponseEntity<?> addOtherActivity(@RequestParam String  otherActiName, @RequestParam String createdOn){
        try {
            OtherActivity otherActivity = otherActivityService.addOtherActivity(otherActiName,createdOn);
            return ResponseEntity.ok(new APIResponse("successful","-"));
        } catch (RuntimeException e){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
