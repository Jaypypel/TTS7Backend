package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.DelegationMeasurables;
import com.TTS.DbWebAPIs.Entity.TaskManagement;
import com.TTS.DbWebAPIs.Entity.TimeShare;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TaskManagementServiceInterface {

    /* it is  used in the existing TTS to get a list of modified task list & a list of processing task list and competed task list though same logic is repeated*/
    List<TaskManagement> getAcceptedTaskList(Long truId, String status);


    TaskManagement updateTaskManagementSeenOnTime(Long taskId);

    TaskManagement updateTaskManagementProcessedOnTime(Long taskId);

    TaskManagement updateTaskManagementApprovedOnTime(Long taskId);

    TaskManagement updateTaskManagementAcceptTime(Long taskId);

    TaskManagement updateTaskManagementCompletedStatus(Long taskId);

    TaskManagement addAssignedTask(Long taskOwnerUserID, Long taskReceivedUserID,
                                           TimeShare timeShareAssociated, String ActivityName, String
                                           taskName, Long
                                           projectId, String
                                           projectName, LocalDate
                                           expectedDate, LocalTime
                                           expectedTime,String
                                           expectedTotalTime,String
                                           description,LocalTime
                                           TaskAssignedOn,LocalTime
                                           actualTotalTime,LocalTime
                                           taskSeenOn,LocalTime
                                           taskCompletedOn,LocalTime
                                           taskAcceptedOn,String
                                           status, DelegationMeasurables
                                           delegationMeasurablesAssociated);

    List<TaskManagement> getSendModificationTaskList(Long touId, String status);

    /*it should be named recivedTaskList*/
    List<TaskManagement> getTaskList(Long truId);


    List<TaskManagement> getDelegatedTaskList(Long touId);



    Integer getPendingTaskCount(Long userId);

    Integer getAcceptedTaskCount(Long userId);

    Integer getApprovedTaskCount(Long userId);

    Integer getCompletedTaskCount(Long userId);


}
