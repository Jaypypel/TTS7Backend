package com.TTS.DbWebAPIs.Controller;


import com.TTS.DbWebAPIs.DTO.ActivityDto;
import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Exceptions.*;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.ActivityServiceInterface;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;

import java.time.LocalDate;

import java.util.List;

//tested all the endpoints
@RequiredArgsConstructor
@RestController
@RequestMapping("/activities1")
public class ActivityController {


       private final ActivityServiceInterface activityService;

       //tested at 1:45pm on 7 oct

        @Async
        @GetMapping("/names")
        ResponseEntity<?> getActivtiesNames() throws DatabaseException, InternalServerException {
//            try {
                List<String> activities = activityService.getActivityNames();
                if (activities == null || activities.isEmpty()){
                    return ResponseEntity
                            .status(HttpStatus.NO_CONTENT)
                            .body(new APIResponse<>("No activities found", null));
                }
                return ResponseEntity.ok(new APIResponse<>("successful",activities));

        }

//        @GetMapping("/names")
//        ResponseEntity<?> getActivtiesNames(
//                @RequestParam(value = "page", defaultValue = "0") int page,
//                @RequestParam(value = "size", defaultValue = "10") int size
//        ) throws SQLException {
//            Pageable pageable = PageRequest.of(page,size);
//            Page<String> activities = activityService.getActivityNames(pageable);
//            return ResponseEntity.ok(new APIResponse("successful",activities));
//
//        }

    @GetMapping("/activityNames")
    ResponseEntity<?> getActivtiesNamesByUserName(@RequestParam(name = "username" , required = true) String username) throws UserNotFoundException, DatabaseException,InternalServerException {

           List<String> activities = activityService.getActivityNamesByUsername(username);
           if (activities == null || activities.isEmpty()){
               return ResponseEntity
                       .status(HttpStatus.NO_CONTENT)
                       .body(new APIResponse<>("No activities found", null));
           }
           return ResponseEntity.ok(new APIResponse<>("successful",activities));


    }

    //tested at 2:45pm on 7 oct
    @GetMapping("/names/{username}")
    ResponseEntity<?>  getActivityList(@PathVariable String username) throws UserNotFoundException, DatabaseException,InternalServerException{
            List<ActivityDto>  activities = activityService.getActivityList(username);
            return ResponseEntity.ok(new APIResponse<>("successful",activities));

        }

        //tested already
        @GetMapping("/activityCount/{username}/{startDate}/{endDate}")
        ResponseEntity<?>  getActivityCount(@PathVariable String username, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate) throws DatabaseException,InternalServerException{

                Integer activityCount = activityService.getActivityCount(username, startDate, endDate);
                if (activityCount == null || activityCount==0  ){
                    return ResponseEntity
                            .status(HttpStatus.NO_CONTENT)
                            .body(new APIResponse<>("No count present", null));
                }
                return ResponseEntity.ok(activityCount);

        }

        //tested already
        @PostMapping("/activity")
        ResponseEntity<?>  addActivity(@RequestParam String username, @RequestParam String actvtyNme, @RequestParam String createdOn) throws UserNotFoundException
    ,DatabaseException ,InternalServerException{

                   Activity activity = activityService.addActivity(username,actvtyNme,createdOn);
                   return ResponseEntity.ok(new APIResponse("successful","-"));


        }

        //tested already
        @GetMapping("/activity/{name}")
        ResponseEntity<?>   getActivity(@PathVariable String name) throws DatabaseException,InternalServerException {
               Activity activity = activityService.getActivity(name);
               if (activity == null){
                   return ResponseEntity
                           .status(HttpStatus.NO_CONTENT)
                           .body(new APIResponse<>("No activities found", null));
               }
               return ResponseEntity.ok(activity);

        }

}
