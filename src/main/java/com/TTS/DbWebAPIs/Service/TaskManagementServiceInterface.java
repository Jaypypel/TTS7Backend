package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.DTO.TaskManagementDTO;
import com.TTS.DbWebAPIs.Entity.DelegationMeasurables;
import com.TTS.DbWebAPIs.Entity.Task;
import com.TTS.DbWebAPIs.Entity.TaskManagement;
import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.InvalidAssignTaskRequestException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Exceptions.UserNotFoundException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface TaskManagementServiceInterface {

    /* it is  used in the existing TTS to get a list of modified task list & a list of processing task list and competed task list though same logic is repeated*/
    List<TaskManagement> getAcceptedTaskList(String username, String status) throws DatabaseException;

  //  TaskManagement updateTaskManagementAssociatedTimeByStatus(Long TaskId, String status);

    TaskManagement updateTaskManagementSeenOnTime(Long taskId) throws DatabaseException;
//
//    TaskManagement updateTaskManagementProcessedOnTime(Long taskId) throws DatabaseException;
//
//    TaskManagement updateTaskManagementApprovedOnTime(Long taskId) throws DatabaseException;
//
//    TaskManagement updateTaskManagementAcceptTime(Long taskId) throws DatabaseException;

    TaskManagement updateTaskManagementStatus(Long taskId,String status) throws NotFoundException, DatabaseException;

    TaskManagement addActualTotalTime(Long assignedTaskId,String actualTotalTime) throws DatabaseException;

    //remove TimeShare timeShareAssociated
    //remove  List<DelegationMeasurables> delegationMeasurablesAssociated
//    TaskManagement addAssignedTask(String taskOwnerUsername, String taskReceivedUsername, String activityName,
//                                   String taskName, String projectId, String projectName, LocalDate expectedDate,
//                                   LocalTime expectedTime, String expectedTotalTime, String description, LocalDateTime taskAssignedOn,
//                                   String actualTotalTime, LocalTime taskSeenOn, String taskCompletedOn, LocalTime taskAcceptedOn,
//                                   LocalTime taskProcessOn, LocalTime taskApproveOn,
//                                   String status);
    TaskManagement addAssignedTask(TaskManagementDTO taskManagementDTO) throws UserNotFoundException, InvalidAssignTaskRequestException, DatabaseException;

    TaskManagement updateModifiedTaskStatusAndDescription(String description, Long taskId) throws DatabaseException, NotFoundException;

    List<TaskManagement> getSendModificationTaskList(String taskOwnerUsername, String status) throws DatabaseException, NotFoundException;

    /*it should be named recivedTaskList*/
    List<TaskManagement> getTaskList(String  taskReceivedUsername) throws DatabaseException, NotFoundException;


    List<TaskManagement> getDelegatedTaskList(String taskOwnerUsername) throws DatabaseException , NotFoundException;

    Integer getTaskCountByStatusAndUsername(String username, String status);
//
//    Integer getPendingTaskCount(String username) throws DatabaseException , NotFoundException;
//
//    Integer getAcceptedTaskCount(String username) throws DatabaseException , NotFoundException;
//
//    Integer getApprovedTaskCount(String username) throws DatabaseException , NotFoundException;
//
//    Integer getCompletedTaskCount(String username) throws DatabaseException , NotFoundException;

    Long getMaxDelegationTaskId() throws DatabaseException , NotFoundException;

    String getActualTotalTime(Long assignedTaskId) throws DatabaseException , NotFoundException;

}
