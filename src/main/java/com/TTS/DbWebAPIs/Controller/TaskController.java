package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.Task;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.TaskName;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.TaskServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskServiceInterface  taskService;

    @GetMapping("/list/name")
    ResponseEntity<?> getTaskNames() {
       try {
           List<String> taskNames = taskService.getTaskNames();
           if (taskNames == null || taskNames.isEmpty()){
               return ResponseEntity
                       .status(HttpStatus.NO_CONTENT)
                       .body(new APIResponse<>("No activities found", null));
           }
           return ResponseEntity.ok(new APIResponse("successful",taskNames) );
       }catch (SQLException ex){
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new APIResponse<>("An error occured while fetching task names. Please try again later.",null));
       } catch (Exception ex){
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
       }
    }

    //tested at 11:36 am on 9th oct
    @GetMapping("/name")
    ResponseEntity<?> getTaskNamesByUsername(@RequestParam String userId){
       try{
           List<String> taskNames = taskService.getTaskNameList(userId);
           if (taskNames == null || taskNames.isEmpty()){
               return ResponseEntity
                       .status(HttpStatus.NO_CONTENT)
                       .body(new APIResponse<>("No activities found", null));
           }
           return ResponseEntity.ok(new APIResponse("successful",taskNames));
       } catch (SQLException ex){
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new APIResponse<>("An error occured while fetching task names. Please try again later.",null));
       } catch (Exception ex){
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
       }
    }

    //tested at 11:53 am on 9th oct
    @GetMapping("/task/count/{username}/{startDate}/{endDate}")
    ResponseEntity<?> getTaskCount(@PathVariable String username, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
       try {
           Integer tskCount = taskService.getTaskCount(username,startDate,endDate);
           if (tskCount == null || tskCount==0){
               return ResponseEntity
                       .status(HttpStatus.NO_CONTENT)
                       .body(new APIResponse<>("No task count is present with given data range", null));
           }
           return ResponseEntity.ok(tskCount);
       }catch (SQLException ex){
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new APIResponse<>("An error occured while fetching task count for submitted date range. Please try again later.",null));
       } catch (Exception ex){
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
       }
    }

    //tested at 1:04 pm on 9th oct
    @GetMapping("/task/frequency/count/{username}/{startDate}/{endDate}")
    ResponseEntity<?> getTaskFreqeuncyCount(@PathVariable String username, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
       try{
           Integer freqeuncyCount = taskService.getTaskFreqeuncyCount(username,startDate,endDate);
           if (freqeuncyCount == null || freqeuncyCount==0){
               return ResponseEntity
                       .status(HttpStatus.NO_CONTENT)
                       .body(new APIResponse<>("No task frequency count is present with given data range", null));
           }
           return ResponseEntity.ok(freqeuncyCount);
       }
        catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occured while fetching task count for submitted date range. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }
    //tested at 11:13 am on 3O sep
    @PostMapping("/task")
    ResponseEntity<?> addTask(@RequestParam String username,@RequestParam Long activityId,@RequestParam String taskName,@RequestParam String createdOn){
        try {
            Task task = taskService.addTask(username,activityId,taskName,createdOn);
            return ResponseEntity.ok(new APIResponse("successful","-"));

        }
           catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occured while fetching task count for submitted date range. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }


}
