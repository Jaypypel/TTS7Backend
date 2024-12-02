package com.TTS.DbWebAPIs.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;


public class TaskManagementDTO {
    private   String taskOwnerUserID;//check

    private   String taskReceivedUserID;//check

    private  String activityName;



    private String taskName;


    private String projectCode;

    private  String projectName;


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

    public String getTaskOwnerUserID() {
        return taskOwnerUserID;
    }

    public String getTaskReceivedUserID() {
        return taskReceivedUserID;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getExpectedDate() {
        return expectedDate;
    }

    public String getExpectedTime() {
        return expectedTime;
    }

    public String getExpectedTotalTime() {
        return expectedTotalTime;
    }

    public String getDescription() {
        return description;
    }

    public String getTaskAssignedOn() {
        return taskAssignedOn;
    }

    public String getActualTotalTime() {
        return actualTotalTime;
    }

    public String getTaskSeenOn() {
        return taskSeenOn;
    }

    public String getTaskCompletedOn() {
        return taskCompletedOn;
    }

    public String getTaskAcceptedOn() {
        return taskAcceptedOn;
    }

    public String getTaskProcessedOn() {
        return taskProcessedOn;
    }

    public String getTasKApprovedOn() {
        return tasKApprovedOn;
    }

    public String getStatus() {
        return status;
    }
}
