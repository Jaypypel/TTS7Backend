package com.TTS.DbWebAPIs.DTO;

import com.TTS.DbWebAPIs.Entity.TaskManagement;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class TaskManagementDTO {


    private Long id;


    private   String taskOwnerUserID;//check


    private   String taskReceivedUserID;//check


    private  String activityName;




    private String taskName;



    private String projectCode;


    private  String projectName;



    private String expectedDate;
    //added new expectedTime



    private String expectedTime;



    private String expectedTotalTime;



    private String description;



    private String taskAssignedOn;



    private String  actualTotalTime;

    
    private String taskSeenOn;


    
    private String taskCompletedOn;


    
    private String taskAcceptedOn;


    
    private String taskProcessedOn;


    
    private String tasKApprovedOn;


    
    private String status;

    private static TaskManagementDTO taskManagementDTO = new TaskManagementDTO();

    public static TaskManagementDTO convertToDTO(TaskManagement taskManagement){
      TaskManagementDTO taskManagementDTO = new TaskManagementDTO();
        taskManagementDTO.setId(taskManagement.getId());
        taskManagementDTO.setTaskOwnerUserID(taskManagement.getTaskOwnerUserID().getUsername());
        taskManagementDTO.setTaskReceivedUserID(taskManagement.getTaskReceivedUserID().getUsername());
        taskManagementDTO.setActivityName(taskManagement.getActivityName());
        taskManagementDTO.setTaskName(taskManagement.getTaskName());
        taskManagementDTO.setProjectCode(taskManagement.getProjectCode());
        taskManagementDTO.setProjectName(taskManagement.getProjectName());
        taskManagementDTO.setExpectedDate(String.valueOf(taskManagement.getExpectedDate()));
        taskManagementDTO.setExpectedTotalTime(taskManagement.getExpectedTotalTime());
        taskManagementDTO.setDescription(taskManagement.getDescription());
        taskManagementDTO.setTaskAssignedOn(taskManagement.getTaskAssignedOn());
        taskManagementDTO.setActualTotalTime(taskManagement.getActualTotalTime());
        taskManagementDTO.setTaskSeenOn(taskManagement.getTaskSeenOn());
        taskManagementDTO.setTaskCompletedOn(taskManagement.getTaskCompletedOn());
        taskManagementDTO.setTaskAcceptedOn(taskManagement.getTaskAcceptedOn());
        taskManagementDTO.setTaskProcessedOn(taskManagement.getTaskProcessedOn());
        taskManagementDTO.setTasKApprovedOn(taskManagement.getTasKApprovedOn());
        taskManagementDTO.setStatus(taskManagement.getStatus());
        return taskManagementDTO;

    }
}
