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
    ResponseEntity<?> addAssignedTask(@RequestBody TaskAssignedDTO taskAssignedDTO){
        System.out.println("Received taskAssignedDTO: " + taskAssignedDTO);
        try {
            String activityName = taskAssignedDTO.getTaskManagement().getActivityName();

            // Check if ActivityName is not null or empty
            if (activityName == null || activityName.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Activity name cannot be null or empty");
            }

            TaskManagement taskManagement = taskManagementService.addAssignedTask(
                    taskAssignedDTO.getTaskManagement().getTaskOwnerUserID().getId(),
                    taskAssignedDTO.getTaskManagement().getTaskReceivedUserID().getId(),
                    activityName,
                    taskAssignedDTO.getTaskManagement().getTaskName(),
                    taskAssignedDTO.getTaskManagement().getProjectId()
                    ,taskAssignedDTO.getTaskManagement().getProjectName(),
                    taskAssignedDTO.getTaskManagement().getExpectedDate(),
                    taskAssignedDTO.getTaskManagement().getExpectedTime(),
                    taskAssignedDTO.getTaskManagement().getExpectedTotalTime(),
                    taskAssignedDTO.getTaskManagement().getDescription(),
                    taskAssignedDTO.getTaskManagement().getTaskAssignedOn(),
                    taskAssignedDTO.getTaskManagement().getActualTotalTime(),
                    taskAssignedDTO.getTaskManagement().getTaskSeenOn(),
                    taskAssignedDTO.getTaskManagement().getTaskCompletedOn(),
                    taskAssignedDTO.getTaskManagement().getTaskAcceptedOn(),
                    taskAssignedDTO.getTaskManagement().getStatus(),taskAssignedDTO.getDelegationMeasurablesLlist());
             return ResponseEntity.ok(taskManagement);
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping(" /list/accepted/{truId}/{status}")
    ResponseEntity<List<TaskManagement>> getAcceptedTaskList(@PathVariable Long  truId, @PathVariable String status){
        List<TaskManagement> taskManagements = taskManagementService.getAcceptedTaskList(truId,status);
        return ResponseEntity.ok(taskManagements);
    }

    @PutMapping("task/{taskID}/seentime/update/")
    ResponseEntity<TaskManagement> updateTaskManagementSeenOnTime(@PathVariable Long  taskID){
        TaskManagement taskManagement = taskManagementService.updateTaskManagementSeenOnTime(taskID);
        return ResponseEntity.ok(taskManagement);
    }

    @PutMapping("task/{taskID}/processedTime/update/")
    ResponseEntity<TaskManagement> updateTaskManagementProcessedOnTime(@PathVariable Long  taskID){
        TaskManagement taskManagement = taskManagementService.updateTaskManagementProcessedOnTime(taskID);
        return ResponseEntity.ok(taskManagement);
    }



    @PutMapping("task/{taskID}/approvedTime/update/")
    ResponseEntity<TaskManagement> updateTaskManagementApprovedOnTime(@PathVariable Long  taskID){
        TaskManagement taskManagement = taskManagementService.updateTaskManagementApprovedOnTime(taskID);
        return ResponseEntity.ok(taskManagement);
    }

    @PutMapping("task/{taskID}/acceptTime/update/")
    ResponseEntity<TaskManagement> updateTaskManagementAcceptTime(@PathVariable Long  taskID){
        TaskManagement taskManagement = taskManagementService.updateTaskManagementAcceptTime(taskID);
        return ResponseEntity.ok(taskManagement);
    }

    @PutMapping("task/{taskID}/status/update/")
    ResponseEntity<TaskManagement> updateTaskManagementCompletedStatus(@PathVariable Long  taskID){
        TaskManagement taskManagement = taskManagementService.updateTaskManagementCompletedStatus(taskID);
        return ResponseEntity.ok(taskManagement);
    }

    @PutMapping("task/{taskID}/{description}/status/update/")
    ResponseEntity<TaskManagement>   updateModifiedTaskStatusAndDescription(@PathVariable String description,
                                                                            @PathVariable Long taskId){
        TaskManagement taskManagement = taskManagementService.updateModifiedTaskStatusAndDescription(description,taskId);
        return ResponseEntity.ok(taskManagement);
    }

    @GetMapping("/{touId}/{status}/modified/list")
    ResponseEntity<List<TaskManagement>> getSendModificationTaskList(@PathVariable Long touId,
                                                                     @PathVariable String status){
        List<TaskManagement> taskManagements = taskManagementService.getSendModificationTaskList(touId,status);
        return ResponseEntity.ok(taskManagements);
    }

    @GetMapping("/{truId}/list")
    ResponseEntity<List<TaskManagement>> getTaskList(@PathVariable Long truId){
        List<TaskManagement> taskManagements = taskManagementService.getTaskList(truId);
        return ResponseEntity.ok(taskManagements);
    }

    @GetMapping("delegated/{truId}/list")
    ResponseEntity<List<TaskManagement>> getDelegatedTaskList(@PathVariable Long touId){
        List<TaskManagement> taskManagements = taskManagementService.getDelegatedTaskList(touId);
        return ResponseEntity.ok(taskManagements);
    }

    @GetMapping("/pending/count/{userId}")
    ResponseEntity<Integer> getPendingTaskCount(Long userId){
        Integer pndTskCnt = taskManagementService.getPendingTaskCount(userId);
        return ResponseEntity.ok(pndTskCnt);
    }

    @GetMapping("/accepted/count/{userId}")
    ResponseEntity<Integer> getAcceptedTaskCount(Long userId){
        Integer aptTskCnt = taskManagementService.getAcceptedTaskCount(userId);
        return ResponseEntity.ok(aptTskCnt);
    }

    @GetMapping("/approved/count/{userId}")
    ResponseEntity<Integer> getApprovedTaskCount(Long userId){
        Integer apvTskCnt = taskManagementService.getApprovedTaskCount(userId);
        return ResponseEntity.ok(apvTskCnt);
    }

    @GetMapping("/completed/count/{userId}")
    ResponseEntity<Integer> getCompletedTaskCount(Long userId){
        Integer cmpTskCnt = taskManagementService.getCompletedTaskCount(userId);
        return ResponseEntity.ok(cmpTskCnt);
    }

    @GetMapping("/task/assigned/maxId")
    ResponseEntity<Long> getMaxDelegationTaskId(){
        Long maxId = taskManagementService.getMaxDelegationTaskId();
        return ResponseEntity.ok(maxId);
    }

    @GetMapping("/time/assigned/{assignedTaskId}")
    ResponseEntity<String> getActualTotalTime(@PathVariable Long assignedTaskId){
        String atlTime = taskManagementService.getActualTotalTime(assignedTaskId);
        return ResponseEntity.ok(atlTime);
    }



}
