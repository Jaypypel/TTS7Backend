package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.DelegationMeasurables;
import com.TTS.DbWebAPIs.Entity.Task;
import com.TTS.DbWebAPIs.Entity.TaskManagement;
import com.TTS.DbWebAPIs.Entity.TimeShare;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface TaskManagementServiceInterface {

    /* it is  used in the existing TTS to get a list of modified task list & a list of processing task list and competed task list though same logic is repeated*/
    List<TaskManagement> getAcceptedTaskList(String username, String status);


    TaskManagement updateTaskManagementSeenOnTime(Long taskId);

    TaskManagement updateTaskManagementProcessedOnTime(Long taskId);

    TaskManagement updateTaskManagementApprovedOnTime(Long taskId);

    TaskManagement updateTaskManagementAcceptTime(Long taskId);

    TaskManagement updateTaskManagementStatus(Long taskId,String status);

    TaskManagement addActualTotalTime(Long assignedTaskId,String actualTotalTime);

    //remove TimeShare timeShareAssociated
    //remove  List<DelegationMeasurables> delegationMeasurablesAssociated
//    TaskManagement addAssignedTask(String taskOwnerUsername, String taskReceivedUsername, String activityName,
//                                   String taskName, String projectId, String projectName, LocalDate expectedDate,
//                                   LocalTime expectedTime, String expectedTotalTime, String description, LocalDateTime taskAssignedOn,
//                                   String actualTotalTime, LocalTime taskSeenOn, String taskCompletedOn, LocalTime taskAcceptedOn,
//                                   LocalTime taskProcessOn, LocalTime taskApproveOn,
//                                   String status);
    TaskManagement addAssignedTask(String taskOwnerUsername, String taskReceivedUsername, String activityName,
                                   String taskName, String projectCode, String projectName, LocalDate expectedDate,
                                   String expectedTime, String expectedTotalTime, String description, String taskAssignedOn,
                                   String actualTotalTime, String taskSeenOn, String taskCompletedOn, String taskAcceptedOn,
                                   String taskProcessOn, String taskApproveOn,
                                   String status);

    TaskManagement updateModifiedTaskStatusAndDescription(String description, Long taskId);

    List<TaskManagement> getSendModificationTaskList(String taskOwnerUsername, String status);

    /*it should be named recivedTaskList*/
    List<TaskManagement> getTaskList(String  taskReceivedUsername);


    List<TaskManagement> getDelegatedTaskList(String taskOwnerUsername);



    Integer getPendingTaskCount(String username);

    Integer getAcceptedTaskCount(String username);

    Integer getApprovedTaskCount(String username);

    Integer getCompletedTaskCount(String username);

    Long getMaxDelegationTaskId();

    String getActualTotalTime(Long assignedTaskId);

}
