package com.TTS.DbWebAPIs.DTO;

import com.TTS.DbWebAPIs.Entity.Project;
import com.TTS.DbWebAPIs.Entity.TaskManagement;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@ToString
public class TaskManagementDTO {


    private Long id;

    @Valid
    @NotNull
    private   String taskOwnerUserID;//check


    @Valid
    @NotBlank
    private   String taskReceivedUserID;//check


    @Column(name = "activity_name")
    @NotBlank(message = "activity name can not accept blank")
    @Size(max = 50, message = "activity name can not exceed 50 characters")
    private  String activityName;


    @Size(max = 100, message = "task name can not exceed 100 characters")
    @NotBlank
    private String taskName;



    @NotBlank(message = "The project code can not be accepted blank")
    @Size(max = 50 , message = "The project code can not exceed 50 characters")
    private String projectCode;

    @NotBlank
    @Size(max = 100, message = "The project code can not exceed 100 characters")
    private  String projectName;



    @NotNull
    private String  expectedDate;
    //added new expectedTime


    @NotBlank
    private String expectedTime;


    @NotBlank
    private String expectedTotalTime;


    @NotBlank(message = "description can not blank")
    @Size(max = 150, message = "description can not exceed 150 characters")
    private String description;


    @NotBlank
    private String taskAssignedOn;


    @NotBlank
    private String  actualTotalTime;

    @NotBlank
    private String taskSeenOn;


    @NotBlank
    private String taskCompletedOn;


    @NotBlank
    private String taskAcceptedOn;


    @NotBlank
    private String taskProcessedOn;


    @NotBlank
    private String tasKApprovedOn;


    @NotBlank
    private String status;


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

    public static TaskManagement convertToTaskmanagmentEntity( TaskManagementDTO taskManagementDTO){
        TaskManagement assignedTaskManagement = new TaskManagement();
        assignedTaskManagement.setActivityName(taskManagementDTO.getActivityName());
        assignedTaskManagement.setTaskName(taskManagementDTO.getTaskName());
        assignedTaskManagement.setProjectName(taskManagementDTO.getProjectName());
        assignedTaskManagement.setProjectCode(taskManagementDTO.getProjectCode());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate expectDate = LocalDate.parse(taskManagementDTO.getExpectedDate(),formatter);
        assignedTaskManagement.setExpectedDate(expectDate);
        assignedTaskManagement.setExpectedTime(taskManagementDTO.getExpectedTime().formatted("hh:mm:a"));
        assignedTaskManagement.setExpectedTotalTime(taskManagementDTO.getExpectedTotalTime());
        assignedTaskManagement.setDescription(taskManagementDTO.getDescription());
        assignedTaskManagement.setTaskAssignedOn(taskManagementDTO.getTaskAssignedOn());
        assignedTaskManagement.setActualTotalTime(taskManagementDTO.getActualTotalTime());
        assignedTaskManagement.setTaskSeenOn(taskManagementDTO.getTaskSeenOn());
        assignedTaskManagement.setTaskCompletedOn(taskManagementDTO.getTaskCompletedOn());
        assignedTaskManagement.setTaskAcceptedOn(taskManagementDTO.getTaskAcceptedOn());
        assignedTaskManagement.setTaskProcessedOn(taskManagementDTO.getTaskProcessedOn());
        assignedTaskManagement.setTasKApprovedOn(taskManagementDTO.getTasKApprovedOn());
        assignedTaskManagement.setStatus(taskManagementDTO.getStatus());
        return assignedTaskManagement;
    }
}
