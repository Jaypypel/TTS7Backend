package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.DelegationMeasurables;
import com.TTS.DbWebAPIs.Entity.Task;
import com.TTS.DbWebAPIs.Entity.TaskManagement;
import com.TTS.DbWebAPIs.Entity.TimeShare;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface TaskManagementServiceInterface {

    /* it is  used in the existing TTS to get a list of modified task list & a list of processing task list and competed task list though same logic is repeated*/
    List<TaskManagement> getAcceptedTaskList(String username, String status) throws SQLException;


    TaskManagement updateTaskManagementSeenOnTime(Long taskId) throws SQLException;

    TaskManagement updateTaskManagementProcessedOnTime(Long taskId) throws SQLException;

    TaskManagement updateTaskManagementApprovedOnTime(Long taskId) throws SQLException;

    TaskManagement updateTaskManagementAcceptTime(Long taskId) throws SQLException;

    TaskManagement updateTaskManagementStatus(Long taskId,String status) throws SQLException;

    TaskManagement addActualTotalTime(Long assignedTaskId,String actualTotalTime) throws SQLException;

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
                                   String status) throws SQLException;

    TaskManagement updateModifiedTaskStatusAndDescription(String description, Long taskId) throws SQLException;

    List<TaskManagement> getSendModificationTaskList(String taskOwnerUsername, String status) throws SQLException;

    /*it should be named recivedTaskList*/
    List<TaskManagement> getTaskList(String  taskReceivedUsername) throws SQLException;


    List<TaskManagement> getDelegatedTaskList(String taskOwnerUsername) throws SQLException;



    Integer getPendingTaskCount(String username) throws SQLException;

    Integer getAcceptedTaskCount(String username) throws SQLException;

    Integer getApprovedTaskCount(String username) throws SQLException;

    Integer getCompletedTaskCount(String username) throws SQLException;

    Long getMaxDelegationTaskId() throws SQLException;

    String getActualTotalTime(Long assignedTaskId) throws SQLException;

}
