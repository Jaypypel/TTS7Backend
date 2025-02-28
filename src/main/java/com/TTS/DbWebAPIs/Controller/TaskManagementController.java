package com.TTS.DbWebAPIs.Controller;


import com.TTS.DbWebAPIs.DTO.TaskManagementDTO;
import com.TTS.DbWebAPIs.Entity.DelegationMeasurables;
import com.TTS.DbWebAPIs.Entity.TaskManagement;
import com.TTS.DbWebAPIs.Exceptions.*;
import com.TTS.DbWebAPIs.Response.APIResponse;
import com.TTS.DbWebAPIs.Service.TaskManagementServiceInterface;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("tasksm1")
public class TaskManagementController {

    private  final TaskManagementServiceInterface taskManagementService;

    //tested at the 4:41 pm on 3rd of oct
    @PostMapping("/taskm")
    ResponseEntity<?> addAssignedTask(@RequestBody @Valid TaskManagementDTO taskAssigned)
            throws UserNotFoundException, DatabaseException, InternalServerException, InvalidAssignTaskRequestException {
            TaskManagement taskManagement = taskManagementService.addAssignedTask(taskAssigned);
            return ResponseEntity.ok(new APIResponse<>("Successful",taskManagement));
    }

    //tested at 3:04pm on 9th oct
    @GetMapping("/list/accepted/{truId}/{status}")
    ResponseEntity<APIResponse> getTasksByTaskReceiveUsernameAndStatus(@PathVariable String truId, @PathVariable String status) throws DatabaseException {

            List<TaskManagement> taskManagements = taskManagementService.getAcceptedTaskList(truId,status);
            if (taskManagements == null || taskManagements.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No tasks found", null));
            }
            return ResponseEntity.ok(new APIResponse<>("Successful",convertToTaskManagementDTOList(taskManagements)));
    }

    //tested at 4:00pm on 9th oct
    @PutMapping("/task/{taskID}/seentime/update/")
    ResponseEntity<?> updateTaskManagementSeenOnTime(@PathVariable Long  taskID) throws DatabaseException {

            TaskManagement taskManagement = taskManagementService.updateTaskManagementSeenOnTime(taskID);
            return ResponseEntity.ok(new APIResponse<>("successful","-"));

    }

    //tested at 4:37 pm on 9th oct
//    @PutMapping("/task/{taskID}/processedTime/update/")
//    ResponseEntity<?> updateTaskManagementProcessedOnTime(@PathVariable Long  taskID){
//
//            TaskManagement taskManagement = taskManagementService.updateTaskManagementProcessedOnTime(taskID);
//            return ResponseEntity.ok(new APIResponse<>("successful",taskManagement));
//
//    }
//
//
//    //tested at 5:15 pm on 9th oct
//    @PutMapping("task/{taskID}/approvedTime/update/")
//    ResponseEntity<?> updateTaskManagementApprovedOnTime(@PathVariable Long  taskID){
//
//            TaskManagement taskManagement = taskManagementService.updateTaskManagementApprovedOnTime(taskID);
//            return ResponseEntity.ok(new APIResponse<>("successful",taskManagement));
//
//    }
//
//    //tested at 4:45 pm on 9th
//    @PutMapping("task/{taskID}/acceptTime/update/")
//    ResponseEntity<?> updateTaskManagementAcceptTime(@PathVariable Long  taskID){
//
//            TaskManagement taskManagement = taskManagementService.updateTaskManagementAcceptTime(taskID);
//            return ResponseEntity.ok(new APIResponse<>("successful",taskManagement));
//
//    }

    //tested at 5:00 pm on 9th oct
    @PutMapping("task/{taskID}/{status}/update/")
    ResponseEntity<?> updateTaskManagementStatus(@PathVariable Long  taskID, @PathVariable String status) throws NotFoundException, DatabaseException, InternalServerException{

           TaskManagement taskManagement = taskManagementService.updateTaskManagementStatus(taskID, status);
           return ResponseEntity.ok(new APIResponse("updated","-"));

    }

    @PutMapping("task/description-status/update")
    ResponseEntity<?>   updateModifiedTaskStatusAndDescription(@RequestParam String description,
                                                               @RequestParam Long taskId) throws   DatabaseException, NotFoundException, InternalServerException{

            TaskManagement taskManagement = taskManagementService.updateModifiedTaskStatusAndDescription(description,taskId);
            return ResponseEntity.ok(new APIResponse("updated","_"));

    }
    //tested at 11:35 am on 10 oct

    //tested at 3:15 pm on 9th oct
    @GetMapping("/{touId}/{status}/modified/list")
    ResponseEntity<?> getTasksByTaskOwnerUsernameAndStatus(@PathVariable String touId,
                                                                     @PathVariable String status) throws DatabaseException, NotFoundException, InternalServerException{
           List<TaskManagement> taskManagements = taskManagementService.getSendModificationTaskList(touId,status);
            if (taskManagements == null || taskManagements.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No tasks found", null));
            }
           return ResponseEntity.ok(new APIResponse("successful",convertToTaskManagementDTOList(taskManagements)));

    }

    //tested at 3:20pm on 9th Oct
    @GetMapping("/{truId}/list")
    ResponseEntity<?> getTaskList(@PathVariable String truId) throws DatabaseException, InternalServerException,NotFoundException{

            List<TaskManagement> taskManagements = taskManagementService.getTaskList(truId);
            if (taskManagements == null || taskManagements.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No tasks found", null));
            }
            return ResponseEntity.ok(new APIResponse("successful",convertToTaskManagementDTOList(taskManagements))  );

    }

    //tested at 3:30 pm on 9th Oct
    @GetMapping("delegated/{touId}/list")
    ResponseEntity<?> getDelegatedTaskList(@PathVariable String touId) throws DatabaseException, InternalServerException{

           List<TaskManagement> taskManagements = taskManagementService.getDelegatedTaskList(touId);
           if (taskManagements == null || taskManagements.isEmpty()){
               return ResponseEntity
                       .status(HttpStatus.NO_CONTENT)
                       .body(new APIResponse<>("No tasks found", null));
           }
           return ResponseEntity.ok(new APIResponse("successful", convertToTaskManagementDTOList(taskManagements)));

    }

    private  List<TaskManagementDTO> convertToTaskManagementDTOList(List<TaskManagement> taskManagements){
        List<TaskManagementDTO> taskManagementDTOs = new ArrayList<>();
        for (TaskManagement taskManagement: taskManagements){
            taskManagementDTOs.add(TaskManagementDTO.convertToDTO(taskManagement));
        }
        return taskManagementDTOs;
    }


    @GetMapping("count/")
    ResponseEntity<?> getCountTaskBasedOnStatus(@RequestParam("username") String username, @RequestParam("status") String status){

        Integer taskCount = taskManagementService.getTaskCountByStatusAndUsername(username,status);
        return ResponseEntity.ok(new APIResponse<>("successful",taskCount));
    }

//    //tested at 11:24 am on 10 oct
//    @GetMapping("/pending/count/{userId}")
//    ResponseEntity<?> getPendingTaskCount(@PathVariable String userId){
//
//            Integer pndTskCnt = taskManagementService.getPendingTaskCount(userId);
//            return ResponseEntity.ok(new APIResponse<>("successful",pndTskCnt));
//
//    }
//
//    //tested at 11:07 am on 10 oct
//    @GetMapping("/accepted/count/{userId}")
//    ResponseEntity<?> getAcceptedTaskCount(@PathVariable String userId){
//
//
//            int aptTskCnt = taskManagementService.getAcceptedTaskCount(userId);
//            return ResponseEntity.ok(new APIResponse<>("successful",aptTskCnt));
//
//    }
//
//    //tested at 11:07 am on 10 oct
//    @GetMapping("/approved/count/{userId}")
//    ResponseEntity<?> getApprovedTaskCount(@PathVariable String userId){
//      Integer apvTskCnt = taskManagementService.getApprovedTaskCount(userId);
//            return ResponseEntity.ok(new APIResponse<>("successful",apvTskCnt));
//
//
//    }
//
//    //tested at 11:07 am on 10 oct
//    @GetMapping("/completed/count/{userId}")
//    ResponseEntity<?> getCompletedTaskCount(@PathVariable String  userId){
//
//
//
//            Integer cmpTskCnt = taskManagementService.getCompletedTaskCount(userId);
//            return ResponseEntity.ok(new APIResponse<>("successful",cmpTskCnt));
//
//    }

    //tested at 3:40 pm on 9th Oct
    @GetMapping("/task/assigned/maxId")
    ResponseEntity<Long> getMaxDelegationTaskId() throws InternalServerException,DatabaseException,NotFoundException {
        Long maxId = taskManagementService.getMaxDelegationTaskId();
        return ResponseEntity.ok(maxId);
    }

    //tested at 3:50 pm on 9th Oct
    @GetMapping("/time/assigned/{assignedTaskId}")
    ResponseEntity<?> getActualTotalTime(@PathVariable Long assignedTaskId) throws InternalServerException,DatabaseException,NotFoundException{

           String atlTime = taskManagementService.getActualTotalTime(assignedTaskId);
           return ResponseEntity.ok(new APIResponse<>("successful",atlTime));

    }

    @PutMapping("/new-actual-time")
    ResponseEntity<?> updateActualTotalTime(@RequestParam Long assignedTaskId, @RequestParam String newActualTotalTime) throws DatabaseException, InternalServerException {

            taskManagementService.addActualTotalTime(assignedTaskId,newActualTotalTime);
            return ResponseEntity.ok(new APIResponse<>("successful","-"));
    }




}
