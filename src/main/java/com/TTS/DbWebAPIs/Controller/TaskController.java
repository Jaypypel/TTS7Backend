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
        List<String> taskNameList = taskService.getTaskNames();
        return ResponseEntity.ok(new APIResponse("successful",taskNameList) );
    }

    //tested at 11:36 am on 9th oct
    @GetMapping("/name")
    ResponseEntity<?> getTaskNamesByUsername(@RequestParam String userId){
       try{
           List<String> taskNameList = taskService.getTaskNameList(userId);
           return ResponseEntity.ok(new APIResponse("successful",taskNameList));
       } catch (RuntimeException e){
           return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
       }
    }

    //tested at 11:53 am on 9th oct
    @GetMapping("/task/count/{username}/{startDate}/{endDate}")
    ResponseEntity<Integer> getTaskCount(@PathVariable String username, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        Integer tskCount = taskService.getTaskCount(username,startDate,endDate);
        return ResponseEntity.ok(tskCount);
    }

    //tested at 1:04 pm on 9th oct
    @GetMapping("/task/frequency/count/{username}/{startDate}/{endDate}")
    ResponseEntity<Integer> getTaskFreqeuncyCount(@PathVariable String username, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        Integer count = taskService.getTaskFreqeuncyCount(username,startDate,endDate);
        return ResponseEntity.ok(count);
    }
    //tested at 11:13 am on 3O sep
    @PostMapping("/task")
    ResponseEntity<?> addTask(@RequestParam String username,@RequestParam Long activityId,@RequestParam String taskName,@RequestParam String createdOn){
        Task task = taskService.addTask(username,activityId,taskName,createdOn);
        return ResponseEntity.ok(new APIResponse("successful","-"));
    }


}
