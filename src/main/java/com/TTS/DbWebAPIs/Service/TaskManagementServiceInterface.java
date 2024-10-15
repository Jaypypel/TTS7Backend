package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.DelegationMeasurables;
import com.TTS.DbWebAPIs.Entity.Task;
import com.TTS.DbWebAPIs.Entity.TaskManagement;
import com.TTS.DbWebAPIs.Entity.TimeShare;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface TaskManagementServiceInterface {

    /* it is  used in the existing TTS to get a list of modified task list & a list of processing task list and competed task list though same logic is repeated*/
    List<String> getAcceptedTaskList(String username, String status);


    TaskManagement updateTaskManagementSeenOnTime(Long taskId);

    TaskManagement updateTaskManagementProcessedOnTime(Long taskId);

    TaskManagement updateTaskManagementApprovedOnTime(Long taskId);

    TaskManagement updateTaskManagementAcceptTime(Long taskId);

    TaskManagement updateTaskManagementCompletedStatus(Long taskId);

    TaskManagement addActualTotalTime(Long assignedTaskId,String actualTotalTime);

    //remove TimeShare timeShareAssociated
    TaskManagement addAssignedTask(String taskOwnerUsername, String taskReceivedUsername, String activityName,
                                   String taskName, String projectId, String projectName, LocalDateTime expectedDate,
                                   LocalTime expectedTime, String expectedTotalTime, String description, LocalTime taskAssignedOn,
                                   String actualTotalTime, LocalTime taskSeenOn, LocalTime taskCompletedOn, LocalTime taskAcceptedOn,
                                   LocalTime taskProcessOn, LocalTime taskApproveOn,
                                   String status, List<DelegationMeasurables> delegationMeasurablesAssociated);

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
