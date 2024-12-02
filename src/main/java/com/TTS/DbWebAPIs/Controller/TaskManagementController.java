package com.TTS.DbWebAPIs.Controller;


import com.TTS.DbWebAPIs.DTO.TaskManagementDTO;
import com.TTS.DbWebAPIs.Entity.DelegationMeasurables;
import com.TTS.DbWebAPIs.Entity.TaskManagement;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.TaskManagementServiceInterface;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("tasksm")
public class TaskManagementController {

    private  final TaskManagementServiceInterface taskManagementService;

    //tested at the 4:41 pm on 3rd of oct
    @PostMapping("/taskm")
    ResponseEntity<APIResponse> addAssignedTask(@RequestBody TaskManagementDTO taskAssigned) {
       System.out.println(taskAssigned.getTaskAssignedOn());


        System.out.println("Received taskAssignedDTO: " + taskAssigned);
        try {
            String activityName = taskAssigned.getActivityName();
            System.out.println(activityName);

//            // Check if ActivityName is not null or empty
//            if (activityName == null || activityName.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Activity name cannot be null or empty");
//            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            LocalDate localDate = LocalDate.parse(taskAssigned.getExpectedDate(),formatter);
            System.out.println("localDate : "+localDate);

            DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime localTime = LocalTime.parse(taskAssigned.getExpectedTime(), timeformatter);

            System.out.println("localTime : "+localTime);

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a", Locale.ENGLISH);
            System.out.println(dateTimeFormatter);
            LocalDateTime taskAssignedOn = LocalDateTime.parse(taskAssigned.getTaskAssignedOn().trim(),dateTimeFormatter);
            LocalDateTime updatedAssignedOn = taskAssignedOn.truncatedTo(ChronoUnit.MINUTES);

            System.out.println("before printing tasking assigned");
            System.out.println("taskAssignedOn : "+taskAssignedOn);
            System.out.println("after printing tasking assigned");
            TaskManagement taskManagement = taskManagementService.addAssignedTask(
                    taskAssigned.getTaskOwnerUserID(),
                    taskAssigned.getTaskReceivedUserID(),
                    activityName,
                    taskAssigned.getTaskName(),
                    taskAssigned.getProjectCode(),
                    taskAssigned.getProjectName(),
                    localDate,
                    localTime,
                    taskAssigned.getExpectedTotalTime(),
                    taskAssigned.getDescription(),
                    taskAssigned.getTaskAssignedOn(),
                    taskAssigned.getActualTotalTime(),
                    taskAssigned.getTaskSeenOn(),
                    taskAssigned.getTaskCompletedOn().toString(),
                    taskAssigned.getTaskAcceptedOn(),
                    taskAssigned.getTaskProcessedOn(),
                    taskAssigned.getTasKApprovedOn(),
                    taskAssigned.getStatus());

            System.out.println("taskManagement : " + taskManagement);
            return ResponseEntity.ok(new APIResponse("Successful",taskManagement));
        } catch (Exception e) {
            System.out.println("getting Exception");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new APIResponse("Error",e.getMessage())  );
        }
    }

    //tested at 3:04pm on 9th oct
    @GetMapping("/list/accepted/{truId}/{status}")
    ResponseEntity<APIResponse> getTasksByTaskReceiveUsernameAndStatus(@PathVariable String truId, @PathVariable String status){
        List<TaskManagement> taskManagements = taskManagementService.getAcceptedTaskList(truId,status);
        return ResponseEntity.ok(new APIResponse("Successful",taskManagements));
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
    @PutMapping("task/{taskID}/{status}/update/")
    ResponseEntity<?> updateTaskManagementStatus(@PathVariable Long  taskID, @PathVariable String status){
       try {
           TaskManagement taskManagement = taskManagementService.updateTaskManagementStatus(taskID, status);
           return ResponseEntity.ok(new APIResponse("updated",null));
       }catch (NotFoundException e){
          return ResponseEntity.status(HttpStatus.CONFLICT).body("task not found" + e.getMessage());
       }
    }

    //tested at 11:35 am on 10 oct
    @PutMapping("task/description-status/update")
    ResponseEntity<?>   updateModifiedTaskStatusAndDescription(@RequestParam String description,
                                                                             @RequestParam Long taskId){
        TaskManagement taskManagement = taskManagementService.updateModifiedTaskStatusAndDescription(description,taskId);
        return ResponseEntity.ok(new APIResponse("updated","_"));
    }

    //tested at 3:15 pm on 9th oct
    @GetMapping("/{touId}/{status}/modified/list")
    ResponseEntity<APIResponse> getTasksByTaskOwnerUsernameAndStatus(@PathVariable String touId,
                                                                     @PathVariable String status){
        List<TaskManagement> taskManagements = taskManagementService.getSendModificationTaskList(touId,status);
        return ResponseEntity.ok(new APIResponse("successful",taskManagements));
    }

    //tested at 3:20pm on 9th Oct
    @GetMapping("/{truId}/list")
    ResponseEntity<APIResponse> getTaskList(@PathVariable String truId){
        List<TaskManagement> taskManagements = taskManagementService.getTaskList(truId);
        return ResponseEntity.ok(new APIResponse("successful",taskManagements)  );
    }

    //tested at 3:30 pm on 9th Oct
    @GetMapping("delegated/{touId}/list")
    ResponseEntity<APIResponse> getDelegatedTaskList(@PathVariable String touId){
        List<TaskManagement> taskManagements = taskManagementService.getDelegatedTaskList(touId);
        return ResponseEntity.ok(new APIResponse("successful", taskManagements));

    }

    //tested at 11:24 am on 10 oct
    @GetMapping("/pending/count/{userId}")
    ResponseEntity<APIResponse> getPendingTaskCount(@PathVariable String userId){
        Integer pndTskCnt = taskManagementService.getPendingTaskCount(userId);
        return ResponseEntity.ok(new APIResponse("successful",pndTskCnt));
    }

    //tested at 11:07 am on 10 oct
    @GetMapping("/accepted/count/{userId}")
    ResponseEntity<APIResponse> getAcceptedTaskCount(@PathVariable String userId){
        Integer aptTskCnt = taskManagementService.getAcceptedTaskCount(userId);
        return ResponseEntity.ok(new APIResponse("successful",aptTskCnt));
    }

    //tested at 11:07 am on 10 oct
    @GetMapping("/approved/count/{userId}")
    ResponseEntity<APIResponse> getApprovedTaskCount(@PathVariable String userId){
        System.out.println("Username: " + userId);
        Integer apvTskCnt = taskManagementService.getApprovedTaskCount(userId);
        return ResponseEntity.ok(new APIResponse("successful",apvTskCnt));
    }

    //tested at 11:07 am on 10 oct
    @GetMapping("/completed/count/{userId}")
    ResponseEntity<APIResponse> getCompletedTaskCount(@PathVariable String  userId){
        Integer cmpTskCnt = taskManagementService.getCompletedTaskCount(userId);
        return ResponseEntity.ok(new APIResponse("successful",cmpTskCnt));
    }

    //tested at 3:40 pm on 9th Oct
    @GetMapping("/task/assigned/maxId")
    ResponseEntity<Long> getMaxDelegationTaskId(){
        Long maxId = taskManagementService.getMaxDelegationTaskId();
        return ResponseEntity.ok(maxId);
    }

    //tested at 3:50 pm on 9th Oct
    @GetMapping("/time/assigned/{assignedTaskId}")
    ResponseEntity<?> getActualTotalTime(@PathVariable Long assignedTaskId){
        String atlTime = taskManagementService.getActualTotalTime(assignedTaskId);
        return ResponseEntity.ok(new APIResponse("successful",atlTime));
    }

    @PutMapping("/new-actual-time")
    ResponseEntity<?> updateActualTotalTime(@RequestParam Long assignedTaskId, @RequestParam String newActualTotalTime){
        taskManagementService.addActualTotalTime(assignedTaskId,newActualTotalTime);
        return ResponseEntity.ok(new APIResponse("successful","-"));
    }




}
