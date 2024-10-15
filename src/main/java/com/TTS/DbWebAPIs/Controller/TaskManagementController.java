package com.TTS.DbWebAPIs.Controller;

import com.TTS.DbWebAPIs.DTO.TaskAssignedDTO;
import com.TTS.DbWebAPIs.Entity.DelegationMeasurables;
import com.TTS.DbWebAPIs.Entity.TaskManagement;
import com.TTS.DbWebAPIs.Service.TaskManagementServiceInterface;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tasksm")
public class TaskManagementController {

    private  final TaskManagementServiceInterface taskManagementService;

    //tested at the 4:41 pm on 3rd of oct
    @PostMapping("/taskm")
    ResponseEntity<?> addAssignedTask(@RequestBody TaskAssignedDTO taskAssignedDTO) {
        System.out.println("Received taskAssignedDTO: " + taskAssignedDTO);
        try {
            String activityName = taskAssignedDTO.getTaskManagement().getActivityName();
            System.out.println(activityName);

//            // Check if ActivityName is not null or empty
//            if (activityName == null || activityName.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Activity name cannot be null or empty");
//            }

            TaskManagement taskManagement = taskManagementService.addAssignedTask(
                    taskAssignedDTO.getTaskManagement().getTaskOwnerUserID().getUsername(),
                    taskAssignedDTO.getTaskManagement().getTaskReceivedUserID().getUsername(),
                    activityName,
                    taskAssignedDTO.getTaskManagement().getTaskName(),
                    taskAssignedDTO.getTaskManagement().getProjectCode(),
                    taskAssignedDTO.getTaskManagement().getProjectName(),
                    taskAssignedDTO.getTaskManagement().getExpectedDate(),
                    taskAssignedDTO.getTaskManagement().getExpectedTime(),
                    taskAssignedDTO.getTaskManagement().getExpectedTotalTime(),
                    taskAssignedDTO.getTaskManagement().getDescription(),
                    taskAssignedDTO.getTaskManagement().getTaskAssignedOn(),
                    taskAssignedDTO.getTaskManagement().getActualTotalTime(),
                    taskAssignedDTO.getTaskManagement().getTaskSeenOn(),
                    taskAssignedDTO.getTaskManagement().getTaskCompletedOn(),
                    taskAssignedDTO.getTaskManagement().getTaskAcceptedOn(),
                    taskAssignedDTO.getTaskManagement().getTaskProcessedOn(),
                    taskAssignedDTO.getTaskManagement().getTasKApprovedOn(),
                    taskAssignedDTO.getTaskManagement().getStatus(), taskAssignedDTO.getDelegationMeasurablesLlist());
            return ResponseEntity.ok(taskManagement);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    //tested at 3:04pm on 9th oct
    @GetMapping("/list/accepted/{truId}/{status}")
    ResponseEntity<List<String>> getAcceptedTaskList(@PathVariable String truId, @PathVariable String status){
        List<String> taskManagements = taskManagementService.getAcceptedTaskList(truId,status);
        return ResponseEntity.ok(taskManagements);
    }

    //tested at 4:00pm on 9th oct
    @PutMapping("/task/{taskID}/seentime/update/")
    ResponseEntity<TaskManagement> updateTaskManagementSeenOnTime(@PathVariable Long  taskID){
        TaskManagement taskManagement = taskManagementService.updateTaskManagementSeenOnTime(taskID);
        return ResponseEntity.ok(taskManagement);
    }

    //tested at 4:37 pm on 9th oct
    @PutMapping("/task/{taskID}/processedTime/update/")
    ResponseEntity<TaskManagement> updateTaskManagementProcessedOnTime(@PathVariable Long  taskID){
        TaskManagement taskManagement = taskManagementService.updateTaskManagementProcessedOnTime(taskID);
        return ResponseEntity.ok(taskManagement);
    }


    //tested at 5:15 pm on 9th oct
    @PutMapping("task/{taskID}/approvedTime/update/")
    ResponseEntity<TaskManagement> updateTaskManagementApprovedOnTime(@PathVariable Long  taskID){
        TaskManagement taskManagement = taskManagementService.updateTaskManagementApprovedOnTime(taskID);
        return ResponseEntity.ok(taskManagement);
    }

    //tested at 4:45 pm on 9th
    @PutMapping("task/{taskID}/acceptTime/update/")
    ResponseEntity<TaskManagement> updateTaskManagementAcceptTime(@PathVariable Long  taskID){
        TaskManagement taskManagement = taskManagementService.updateTaskManagementAcceptTime(taskID);
        return ResponseEntity.ok(taskManagement);
    }

    //tested at 5:00 pm on 9th oct
    @PutMapping("task/{taskID}/status/update/")
    ResponseEntity<TaskManagement> updateTaskManagementCompletedStatus(@PathVariable Long  taskID){
        TaskManagement taskManagement = taskManagementService.updateTaskManagementCompletedStatus(taskID);
        return ResponseEntity.ok(taskManagement);
    }

    //tested at 11:35 am on 10 oct
    @PutMapping("task/{taskId}/{description}/status/update/")
    ResponseEntity<TaskManagement>   updateModifiedTaskStatusAndDescription(@PathVariable String description,
                                                                            @PathVariable Long taskId){
        TaskManagement taskManagement = taskManagementService.updateModifiedTaskStatusAndDescription(description,taskId);
        return ResponseEntity.ok(taskManagement);
    }

    //tested at 3:15 pm on 9th oct
    @GetMapping("/{touId}/{status}/modified/list")
    ResponseEntity<List<TaskManagement>> getSendModificationTaskList(@PathVariable String touId,
                                                                     @PathVariable String status){
        List<TaskManagement> taskManagements = taskManagementService.getSendModificationTaskList(touId,status);
        return ResponseEntity.ok(taskManagements);
    }

    //tested at 3:20pm on 9th Oct
    @GetMapping("/{truId}/list")
    ResponseEntity<List<TaskManagement>> getTaskList(@PathVariable String truId){
        List<TaskManagement> taskManagements = taskManagementService.getTaskList(truId);
        return ResponseEntity.ok(taskManagements);
    }

    //tested at 3:30 pm on 9th Oct
    @GetMapping("delegated/{touId}/list")
    ResponseEntity<List<TaskManagement>> getDelegatedTaskList(@PathVariable String touId){
        List<TaskManagement> taskManagements = taskManagementService.getDelegatedTaskList(touId);
        return ResponseEntity.ok(taskManagements);
    }

    //tested at 11:24 am on 10 oct
    @GetMapping("/pending/count/{userId}")
    ResponseEntity<Integer> getPendingTaskCount(@PathVariable String userId){
        Integer pndTskCnt = taskManagementService.getPendingTaskCount(userId);
        return ResponseEntity.ok(pndTskCnt);
    }

    //tested at 11:07 am on 10 oct
    @GetMapping("/accepted/count/{userId}")
    ResponseEntity<Integer> getAcceptedTaskCount(@PathVariable String userId){
        Integer aptTskCnt = taskManagementService.getAcceptedTaskCount(userId);
        return ResponseEntity.ok(aptTskCnt);
    }

    //tested at 11:07 am on 10 oct
    @GetMapping("/approved/count/{userId}")
    ResponseEntity<Integer> getApprovedTaskCount(@PathVariable String userId){
        System.out.println("Username: " + userId);
        Integer apvTskCnt = taskManagementService.getApprovedTaskCount(userId);
        return ResponseEntity.ok(apvTskCnt);
    }

    //tested at 11:07 am on 10 oct
    @GetMapping("/completed/count/{userId}")
    ResponseEntity<Integer> getCompletedTaskCount(@PathVariable String  userId){
        Integer cmpTskCnt = taskManagementService.getCompletedTaskCount(userId);
        return ResponseEntity.ok(cmpTskCnt);
    }

    //tested at 3:40 pm on 9th Oct
    @GetMapping("/task/assigned/maxId")
    ResponseEntity<Long> getMaxDelegationTaskId(){
        Long maxId = taskManagementService.getMaxDelegationTaskId();
        return ResponseEntity.ok(maxId);
    }

    //tested at 3:50 pm on 9th Oct
    @GetMapping("/time/assigned/{assignedTaskId}")
    ResponseEntity<String> getActualTotalTime(@PathVariable Long assignedTaskId){
        String atlTime = taskManagementService.getActualTotalTime(assignedTaskId);
        return ResponseEntity.ok(atlTime);
    }



}
