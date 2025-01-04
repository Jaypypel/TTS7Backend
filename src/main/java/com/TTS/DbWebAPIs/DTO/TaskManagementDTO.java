package com.TTS.DbWebAPIs.DTO;

import com.TTS.DbWebAPIs.Entity.TaskManagement;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


public class TaskManagementDTO {

    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private   String taskOwnerUserID;//check

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setActualTotalTime(String actualTotalTime) {
        this.actualTotalTime = actualTotalTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
    }

    public void setExpectedTime(String expectedTime) {
        this.expectedTime = expectedTime;
    }

    public void setExpectedTotalTime(String expectedTotalTime) {
        this.expectedTotalTime = expectedTotalTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTaskAcceptedOn(String taskAcceptedOn) {
        this.taskAcceptedOn = taskAcceptedOn;
    }

    public void setTasKApprovedOn(String tasKApprovedOn) {
        this.tasKApprovedOn = tasKApprovedOn;
    }

    public void setTaskAssignedOn(String taskAssignedOn) {
        this.taskAssignedOn = taskAssignedOn;
    }

    public void setTaskCompletedOn(String taskCompletedOn) {
        this.taskCompletedOn = taskCompletedOn;
    }

    public static void setTaskManagementDTO(TaskManagementDTO taskManagementDTO) {
        TaskManagementDTO.taskManagementDTO = taskManagementDTO;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskOwnerUserID(String taskOwnerUserID) {
        this.taskOwnerUserID = taskOwnerUserID;
    }

    public void setTaskProcessedOn(String taskProcessedOn) {
        this.taskProcessedOn = taskProcessedOn;
    }

    public void setTaskReceivedUserID(String taskReceivedUserID) {
        this.taskReceivedUserID = taskReceivedUserID;
    }

    public void setTaskSeenOn(String taskSeenOn) {
        this.taskSeenOn = taskSeenOn;
    }

    @Getter
    private   String taskReceivedUserID;//check

    @Getter
    private  String activityName;



    @Getter
    private String taskName;


    @Getter
    private String projectCode;

    @Getter
    private  String projectName;


    @Getter
    private String expectedDate;
    //added new expectedTime

    @Override
    public String toString() {
        return "TaskManagementDTO{" +
                "taskOwnerUserID='" + taskOwnerUserID + '\'' +
                ", taskReceivedUserID='" + taskReceivedUserID + '\'' +
                ", activityName='" + activityName + '\'' +
                ", taskName='" + taskName + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", projectName='" + projectName + '\'' +
                ", expectedDate='" + expectedDate + '\'' +
                ", expectedTime='" + expectedTime + '\'' +
                ", expectedTotalTime='" + expectedTotalTime + '\'' +
                ", description='" + description + '\'' +
                ", taskAssignedOn=" + taskAssignedOn +
                ", actualTotalTime='" + actualTotalTime + '\'' +
                ", taskSeenOn=" + taskSeenOn +
                ", taskCompletedOn=" + taskCompletedOn +
                ", taskAcceptedOn=" + taskAcceptedOn +
                ", taskProcessedOn=" + taskProcessedOn +
                ", tasKApprovedOn=" + tasKApprovedOn +
                ", status='" + status + '\'' +
                '}';
    }

    @Getter
    private String expectedTime;


    @Getter
    private String expectedTotalTime;


    @Getter
    private String description;


    @Getter
    private String taskAssignedOn;


    @Getter
    private String  actualTotalTime;

    @Getter
    private String taskSeenOn;


    @Getter
    private String taskCompletedOn;


    @Getter
    private String taskAcceptedOn;


    @Getter
    private String taskProcessedOn;


    @Getter
    private String tasKApprovedOn;


    @Getter
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
        taskManagementDTO.setProjectCode(taskManagement.getProjectName());
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
