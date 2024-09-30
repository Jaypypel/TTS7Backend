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
@RequestMapping("tasks")
public class TaskController {

    private final TaskServiceInterface  taskService;

    @GetMapping("/list/name")
    ResponseEntity<List<TaskName>> getTaskNameList(String userId){
        List<TaskName> taskNameList = taskService.getTaskNameList(userId);
        return ResponseEntity.ok(taskNameList);
    }

    @GetMapping("/task/count/{userId}/{startDate}/{endDate}")
    ResponseEntity<Integer> getTaskCount(@PathVariable Long userId, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        Integer tskCount = taskService.getTaskCount(userId,startDate,endDate);
        return ResponseEntity.ok(tskCount);
    }

    @GetMapping("/task/frequency/count/{userId}/{startDate}/{endDate}")
    ResponseEntity<Integer> getTaskFreqeuncyCount(@PathVariable Long userId, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        Integer count = taskService.getTaskFreqeuncyCount(userId,startDate,endDate);
        return ResponseEntity.ok(count);
    }

    @PostMapping("/task/{userId}/{activityId}/{taskName}/{createdOn}")
    ResponseEntity<Task> addTask(Long userId, Long activityId, String taskName, LocalTime creatdOn){
        Task task = taskService.addTask(userId,activityId,taskName,creatdOn);
        return ResponseEntity.ok(task);
    }


}
