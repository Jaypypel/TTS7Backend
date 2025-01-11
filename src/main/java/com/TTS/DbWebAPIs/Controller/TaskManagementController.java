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
@RequestMapping("tasksm")
public class TaskManagementController {

    private  final TaskManagementServiceInterface taskManagementService;

    //tested at the 4:41 pm on 3rd of oct
    @PostMapping("/taskm")
    ResponseEntity<?> addAssignedTask(@RequestBody TaskManagementDTO taskAssigned) {
       System.out.println(taskAssigned.getTaskAssignedOn());


        System.out.println("Received taskAssignedDTO: " + taskAssigned);
        try {
            String activityName = taskAssigned.getActivityName();
            System.out.println(activityName);

//            // Check if ActivityName is not null or empty
//            if (activityName == null || activityName.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Activity name cannot be null or empty");
//            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate expectDate = LocalDate.parse(taskAssigned.getExpectedDate(),formatter);
             TaskManagement taskManagement = taskManagementService.addAssignedTask(
                    taskAssigned.getTaskOwnerUserID(),
                    taskAssigned.getTaskReceivedUserID(),
                    activityName,
                    taskAssigned.getTaskName(),
                    taskAssigned.getProjectCode(),
                    taskAssigned.getProjectName(),
                    expectDate,
                    taskAssigned.getExpectedTime(),
                    taskAssigned.getExpectedTotalTime(),
                    taskAssigned.getDescription(),
                    taskAssigned.getTaskAssignedOn(),
                    taskAssigned.getActualTotalTime(),
                    taskAssigned.getTaskSeenOn(),
                    taskAssigned.getTaskCompletedOn(),
                    taskAssigned.getTaskAcceptedOn(),
                    taskAssigned.getTaskProcessedOn(),
                    taskAssigned.getTasKApprovedOn(),
                    taskAssigned.getStatus());

            System.out.println("taskManagement : " + taskManagement);
            return ResponseEntity.ok(new APIResponse<>("Successful",taskManagement));
        } catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while fetching assigning task. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    //tested at 3:04pm on 9th oct
    @GetMapping("/list/accepted/{truId}/{status}")
    ResponseEntity<APIResponse> getTasksByTaskReceiveUsernameAndStatus(@PathVariable String truId, @PathVariable String status){
        try{
            List<TaskManagement> taskManagements = taskManagementService.getAcceptedTaskList(truId,status);
            if (taskManagements == null || taskManagements.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No tasks found", null));
            }
            return ResponseEntity.ok(new APIResponse("Successful",convertToTaskManagementDTOList(taskManagements)));
        } catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while fetching tasks. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    //tested at 4:00pm on 9th oct
    @PutMapping("/task/{taskID}/seentime/update/")
    ResponseEntity<?> updateTaskManagementSeenOnTime(@PathVariable Long  taskID){
        try {
            TaskManagement taskManagement = taskManagementService.updateTaskManagementSeenOnTime(taskID);
            return ResponseEntity.ok(new APIResponse<>("successful","-"));

        }
         catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while updating seen time. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    //tested at 4:37 pm on 9th oct
    @PutMapping("/task/{taskID}/processedTime/update/")
    ResponseEntity<?> updateTaskManagementProcessedOnTime(@PathVariable Long  taskID){
        try {
            TaskManagement taskManagement = taskManagementService.updateTaskManagementProcessedOnTime(taskID);
            return ResponseEntity.ok(new APIResponse<>("successful",taskManagement));
        } catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while updating processing time. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }


    //tested at 5:15 pm on 9th oct
    @PutMapping("task/{taskID}/approvedTime/update/")
    ResponseEntity<?> updateTaskManagementApprovedOnTime(@PathVariable Long  taskID){
        try {
            TaskManagement taskManagement = taskManagementService.updateTaskManagementApprovedOnTime(taskID);
            return ResponseEntity.ok(new APIResponse<>("successful",taskManagement));
        } catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while updating approved time. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    //tested at 4:45 pm on 9th
    @PutMapping("task/{taskID}/acceptTime/update/")
    ResponseEntity<?> updateTaskManagementAcceptTime(@PathVariable Long  taskID){
        try {
            TaskManagement taskManagement = taskManagementService.updateTaskManagementAcceptTime(taskID);
            return ResponseEntity.ok(new APIResponse<>("successful",taskManagement));
        } catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while updating accept time. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    //tested at 5:00 pm on 9th oct
    @PutMapping("task/{taskID}/{status}/update/")
    ResponseEntity<?> updateTaskManagementStatus(@PathVariable Long  taskID, @PathVariable String status){
       try {
           TaskManagement taskManagement = taskManagementService.updateTaskManagementStatus(taskID, status);
           return ResponseEntity.ok(new APIResponse("updated","-"));
       }catch (SQLException ex){
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new APIResponse<>("An error occurred while updating status of task. Please try again later.",null));
       } catch (Exception ex){
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
       }
    }

    @PutMapping("task/description-status/update")
    ResponseEntity<?>   updateModifiedTaskStatusAndDescription(@RequestParam String description,
                                                               @RequestParam Long taskId){
        try{
            TaskManagement taskManagement = taskManagementService.updateModifiedTaskStatusAndDescription(description,taskId);
            return ResponseEntity.ok(new APIResponse("updated","_"));
        }catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while updating description of task. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }
    //tested at 11:35 am on 10 oct

    //tested at 3:15 pm on 9th oct
    @GetMapping("/{touId}/{status}/modified/list")
    ResponseEntity<?> getTasksByTaskOwnerUsernameAndStatus(@PathVariable String touId,
                                                                     @PathVariable String status){
        try{
           List<TaskManagement> taskManagements = taskManagementService.getSendModificationTaskList(touId,status);
            if (taskManagements == null || taskManagements.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No tasks found", null));
            }
           return ResponseEntity.ok(new APIResponse("successful",convertToTaskManagementDTOList(taskManagements)));
       }catch (SQLException ex){
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new APIResponse<>("An error occurred while getting  tasks. Please try again later.",null));
       } catch (Exception ex){
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
       }
    }

    //tested at 3:20pm on 9th Oct
    @GetMapping("/{truId}/list")
    ResponseEntity<?> getTaskList(@PathVariable String truId){
        try{
            List<TaskManagement> taskManagements = taskManagementService.getTaskList(truId);
            if (taskManagements == null || taskManagements.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No tasks found", null));
            }
            return ResponseEntity.ok(new APIResponse("successful",convertToTaskManagementDTOList(taskManagements))  );
        }catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while getting  tasks. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    //tested at 3:30 pm on 9th Oct
    @GetMapping("delegated/{touId}/list")
    ResponseEntity<?> getDelegatedTaskList(@PathVariable String touId){
       try{
           List<TaskManagement> taskManagements = taskManagementService.getDelegatedTaskList(touId);
           if (taskManagements == null || taskManagements.isEmpty()){
               return ResponseEntity
                       .status(HttpStatus.NO_CONTENT)
                       .body(new APIResponse<>("No tasks found", null));
           }
           return ResponseEntity.ok(new APIResponse("successful", convertToTaskManagementDTOList(taskManagements)));

       }
        catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while getting  tasks. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    private  List<TaskManagementDTO> convertToTaskManagementDTOList(List<TaskManagement> taskManagements){
        List<TaskManagementDTO> taskManagementDTOs = new ArrayList<>();
        for (TaskManagement taskManagement: taskManagements){
            taskManagementDTOs.add(TaskManagementDTO.convertToDTO(taskManagement));
        }
        return taskManagementDTOs;
    }

    //tested at 11:24 am on 10 oct
    @GetMapping("/pending/count/{userId}")
    ResponseEntity<?> getPendingTaskCount(@PathVariable String userId){
        try{
            Integer pndTskCnt = taskManagementService.getPendingTaskCount(userId);
            if(pndTskCnt == null || pndTskCnt == 0){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No tasks pending ", null));
            }
            return ResponseEntity.ok(new APIResponse("successful",pndTskCnt));
        }
        catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while getting pending tasks count. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    //tested at 11:07 am on 10 oct
    @GetMapping("/accepted/count/{userId}")
    ResponseEntity<?> getAcceptedTaskCount(@PathVariable String userId){

        try{
            Integer aptTskCnt = taskManagementService.getAcceptedTaskCount(userId);
            if(aptTskCnt == null || aptTskCnt == 0){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No task  accepted ", null));
            }
            return ResponseEntity.ok(new APIResponse<>("successful",aptTskCnt));
        }
        catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while getting accepted tasks count. Please try again later.",ex.getMessage()));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    //tested at 11:07 am on 10 oct
    @GetMapping("/approved/count/{userId}")
    ResponseEntity<?> getApprovedTaskCount(@PathVariable String userId){

        try{
            Integer apvTskCnt = taskManagementService.getApprovedTaskCount(userId);
            if(apvTskCnt == null || apvTskCnt == 0){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No task approved ", null));
            }
            return ResponseEntity.ok(new APIResponse<>("successful",apvTskCnt));
        }
        catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while getting approved tasks count. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }

    }

    //tested at 11:07 am on 10 oct
    @GetMapping("/completed/count/{userId}")
    ResponseEntity<?> getCompletedTaskCount(@PathVariable String  userId){


        try{
            Integer cmpTskCnt = taskManagementService.getCompletedTaskCount(userId);
            if(cmpTskCnt == null || cmpTskCnt == 0){
                return ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .body(new APIResponse<>("No task completed ", null));
            }
            return ResponseEntity.ok(new APIResponse<>("successful",cmpTskCnt));
        }
        catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while getting completed tasks count. Please try again later.",null));
        } catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    //tested at 3:40 pm on 9th Oct
    @GetMapping("/task/assigned/maxId")
    ResponseEntity<Long> getMaxDelegationTaskId() throws SQLException {
        Long maxId = taskManagementService.getMaxDelegationTaskId();
        return ResponseEntity.ok(maxId);
    }

    //tested at 3:50 pm on 9th Oct
    @GetMapping("/time/assigned/{assignedTaskId}")
    ResponseEntity<?> getActualTotalTime(@PathVariable Long assignedTaskId){
       try{
           String atlTime = taskManagementService.getActualTotalTime(assignedTaskId);
           return ResponseEntity.ok(new APIResponse<>("successful",atlTime));
       }
       catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while getting actual total time. Please try again later.",null));
        }
       catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }

    @PutMapping("/new-actual-time")
    ResponseEntity<?> updateActualTotalTime(@RequestParam Long assignedTaskId, @RequestParam String newActualTotalTime){
        try{
            taskManagementService.addActualTotalTime(assignedTaskId,newActualTotalTime);
            return ResponseEntity.ok(new APIResponse<>("successful","-"));
        }
         catch (SQLException ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An error occurred while getting actual total time. Please try again later.",null));
        }
       catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIResponse<>("An unexpected error occurred. Please contact support.", null));
        }
    }




}
