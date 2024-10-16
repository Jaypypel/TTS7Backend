package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.Entity.Task;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.TaskName;
import com.TTS.DbWebAPIs.Service.TaskServiceInterface;
import lombok.RequiredArgsConstructor;
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
    ResponseEntity<List<String>> getTaskNames() {
        List<String> taskNameList = taskService.getTaskNames();
        return ResponseEntity.ok(taskNameList);
    }

    //tested at 11:36 am on 9th oct
    @GetMapping("/list/name/{userId}")
    ResponseEntity<List<String>> getTaskNameList(@PathVariable String userId){
        List<String> taskNameList = taskService.getTaskNameList(userId);
        return ResponseEntity.ok(taskNameList);
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
    @PostMapping("/task/{username}/{activityId}/{taskName}/{createdOn}")
    ResponseEntity<Task> addTask(@PathVariable String username,@PathVariable Long activityId,@PathVariable String taskName,@PathVariable LocalTime createdOn){
        Task task = taskService.addTask(username,activityId,taskName,createdOn);
        return ResponseEntity.ok(task);
    }


}
