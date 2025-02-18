package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.Task;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.InternalServerException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Exceptions.UserNotFoundException;
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
    ResponseEntity<?> getTaskNames() throws DatabaseException, InternalServerException {
           List<String> taskNames = taskService.getTaskNames();
           if (taskNames == null || taskNames.isEmpty()){
               return ResponseEntity
                       .status(HttpStatus.NO_CONTENT)
                       .body(new APIResponse<>("No activities found", null));
           }
           return ResponseEntity.ok(new APIResponse<>("successful",taskNames) );
    }

    //tested at 11:36 am on 9th oct
    @GetMapping("/name")
    ResponseEntity<?> getTaskNamesByUsername(@RequestParam String userId) throws DatabaseException , InternalServerException {
           List<String> taskNames = taskService.getTaskNameList(userId);
           if (taskNames == null || taskNames.isEmpty()){
               return ResponseEntity
                       .status(HttpStatus.NO_CONTENT)
                       .body(new APIResponse<>("No activities found", null));
           }
           return ResponseEntity.ok(new APIResponse<>("successful",taskNames));

    }

    //tested at 11:53 am on 9th oct
    @GetMapping("/task/count/{username}/{startDate}/{endDate}")
    ResponseEntity<?> getTaskCount(@PathVariable String username, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate)
            throws DatabaseException , InternalServerException{
           Integer tskCount = taskService.getTaskCount(username,startDate,endDate);
           if (tskCount == null || tskCount==0){
               return ResponseEntity
                       .status(HttpStatus.NO_CONTENT)
                       .body(new APIResponse<>("No task count is present with given data range", null));
           }
           return ResponseEntity.ok(tskCount);

    }

    //tested at 1:04 pm on 9th oct
    @GetMapping("/task/frequency/count/{username}/{startDate}/{endDate}")
    ResponseEntity<?> getTaskFreqeuncyCount(@PathVariable String username, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate) throws
            DatabaseException , InternalServerException {
           Integer freqeuncyCount = taskService.getTaskFreqeuncyCount(username,startDate,endDate);
           if (freqeuncyCount == null || freqeuncyCount==0){
               return ResponseEntity
                       .status(HttpStatus.NO_CONTENT)
                       .body(new APIResponse<>("No task frequency count is present with given data range", null));
           }
           return ResponseEntity.ok(freqeuncyCount);
    }
    //tested at 11:13 am on 3O sep
    @PostMapping("/task")
    ResponseEntity<?> addTask(@RequestParam String username,@RequestParam Long activityId,@RequestParam String taskName,@RequestParam String createdOn)
            throws DatabaseException, NotFoundException, UserNotFoundException , InternalServerException {
            Task task = taskService.addTask(username,activityId,taskName,createdOn);
            return ResponseEntity.ok(new APIResponse<>("successful","-"));

    }


}
