package com.TTS.DbWebAPIs.Controller;


import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Exceptions.AlreadyExistException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.ActivityServiceInterface;
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
@RequestMapping("/activities")
public class ActivityController {


       private final ActivityServiceInterface activityService;

       //tested at 1:45pm on 7 oct

        @Async
        @GetMapping("/names")
        ResponseEntity<?> getActivtiesNames() throws Exception{
//            try {
                List<String> activities = activityService.getActivityNames();
                if (activities == null || activities.isEmpty()){
                    return ResponseEntity
                            .status(HttpStatus.NO_CONTENT)
                            .body(new APIResponse<>("No activities found", null));
                }
                return ResponseEntity.ok(new APIResponse<>("successful",activities));
//            }catch (SQLException ex){
//                return ResponseEntity
//                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                        .body(new APIResponse<>("An error occured while fetching activity names. Please try again later.",null));
//            } catch (Exception ex){
//                return ResponseEntity
//                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                        .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
//            }
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
    ResponseEntity<?> getActivtiesNamesByUserName(@RequestParam(name = "username" , required = true) String username) throws SQLException {
       try {
           List<String> activities = activityService.getActivityNamesByUsername(username);
           for (String activity : activities){
               System.out.println("activites" + activity);
           }
           if (activities == null || activities.isEmpty()){
               return ResponseEntity
                       .status(HttpStatus.NO_CONTENT)
                       .body(new APIResponse<>("No activities found", null));
           }
           return ResponseEntity.ok(new APIResponse<>("successful",activities));
       }catch (SQLException ex){
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new APIResponse<>("An error occured while fetching activity names. Please try again later.",null));
       } catch (Exception ex){
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
       }

    }

    //tested at 2:45pm on 7 oct
    @GetMapping("/names/{username}")
    ResponseEntity<?>  getActivityList(@PathVariable String username) throws SQLException{
        try {
            List<Activity>  activities = activityService.getActivityList(username);
            if (activities == null || activities.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No activities found", null));
            }
            return ResponseEntity.ok(new APIResponse<>("successful",activities));

        }catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occured while fetching activity . Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
        }

        //tested already
        @GetMapping("/activityCount/{username}/{startDate}/{endDate}")
        ResponseEntity<?>  getActivityCount(@PathVariable String username, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate) throws SQLException{
            try {
                Integer activityCount = activityService.getActivityCount(username, startDate, endDate);
                if (activityCount == null || activityCount==0  ){
                    return ResponseEntity
                            .status(HttpStatus.NO_CONTENT)
                            .body(new APIResponse<>("No count present", null));
                }
                return ResponseEntity.ok(activityCount);
            }catch (SQLException ex){
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new APIResponse<>("An error occured while fetching activity names. Please try again later.",null));
            } catch (Exception ex){
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
            }
        }

        //tested already
        @PostMapping("/activity")
        ResponseEntity<?>  addActivity(@RequestParam String username, @RequestParam String actvtyNme, @RequestParam String createdOn){
               try {
                   Activity activity = activityService.addActivity(username,actvtyNme,createdOn);
                   return ResponseEntity.ok(new APIResponse("successful","-"));
               } catch (SQLException ex){
                   return ResponseEntity
                           .status(HttpStatus.INTERNAL_SERVER_ERROR)
                           .body(new APIResponse<>("An error occured while fetching activity names. Please try again later.",null));
               } catch (Exception ex){
                   return ResponseEntity
                           .status(HttpStatus.INTERNAL_SERVER_ERROR)
                           .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
               }

        }

        //tested already
        @GetMapping("/activity/{name}")
        ResponseEntity<?>   getActivity(@PathVariable String name) {
           try {
               Activity activity = activityService.getActivity(name);
               if (activity == null){
                   return ResponseEntity
                           .status(HttpStatus.NO_CONTENT)
                           .body(new APIResponse<>("No activities found", null));
               }
               return ResponseEntity.ok(activity);
           } catch (SQLException ex){
               return ResponseEntity
                       .status(HttpStatus.INTERNAL_SERVER_ERROR)
                       .body(new APIResponse<>("An error occured while fetching activity names. Please try again later.",null));
           } catch (Exception ex){
               return ResponseEntity
                       .status(HttpStatus.INTERNAL_SERVER_ERROR)
                       .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
           }
        }

}
