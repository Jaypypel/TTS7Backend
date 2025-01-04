package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.*;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Repository.*;
import com.TTS.DbWebAPIs.Util.DateAndTimeConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskManagementService implements TaskManagementServiceInterface{

    private  final TaskManagementRepository taskManagementRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private  final ProjectRepository projectRepository;
    private final DelegationMeasurablesRepository delegationMeasurablesRepository;

    /*the function is  used in existing tts app for the receivedModfifcationtAskList,*/
    @Override
    public List<TaskManagement> getAcceptedTaskList(String taskReceivedUsername, String status) throws SQLException {
        return taskManagementRepository.findByUserUsernameAndStatus(taskReceivedUsername,status);
    }

    @Override
    public TaskManagement updateTaskManagementStatus(Long taskId, String status)  throws SQLException{
        TaskManagement existingTaskManagement = taskManagementRepository.findById(taskId).orElseThrow(() -> new NotFoundException("task not found"));
        existingTaskManagement.setStatus(status);
        if(isStatusApproved(existingTaskManagement,status)) return taskManagementRepository
                .save(existingTaskManagement);
        if (isStatusAccepted(existingTaskManagement,status)) return taskManagementRepository
                .save(existingTaskManagement);
        if (isStatusCompleted(existingTaskManagement,status)) return taskManagementRepository
                .save(existingTaskManagement);
        if (isStatusInProcess(existingTaskManagement,status)) return taskManagementRepository
                .save(existingTaskManagement);
        if (isStatusNotSeen(existingTaskManagement,status)) return taskManagementRepository
                .save(existingTaskManagement);
        if (isStatusUnApproved(existingTaskManagement,status)) return taskManagementRepository
                .save(existingTaskManagement);
        return existingTaskManagement;
    }

    private boolean isStatusNotSeen(TaskManagement existingTaskManagement , String status) {
        if(!status.equals("Accepted")) return  false;
        existingTaskManagement.setTaskAcceptedOn(DateAndTimeConfig.getCurrentDateAndTime());
        return true;
    }

    private boolean isStatusAccepted(TaskManagement taskManagement, String status){
        if(!status.equals("Accepted")) return  false;
        taskManagement.setTaskAcceptedOn(DateAndTimeConfig.getCurrentDateAndTime());
        return true;
    }

    private boolean isStatusUnApproved(TaskManagement taskManagement, String status){
        if(!status.equals("Unapproved")) return  false;
        taskManagement.setTasKApprovedOn(DateAndTimeConfig.getCurrentDateAndTime());
        return true;
    }

    private boolean isStatusApproved(TaskManagement taskManagement, String status){
        if(!status.equals("Approved")) return  false;
        taskManagement.setTasKApprovedOn(DateAndTimeConfig.getCurrentDateAndTime());
        return true;
    }

    private boolean isStatusCompleted(TaskManagement taskManagement, String status){
        if(!status.equals("Completed")) return  false;
        taskManagement.setTaskCompletedOn(DateAndTimeConfig.getCurrentDateAndTime());
        return true;
    }

    private boolean isStatusInProcess(TaskManagement taskManagement, String status){
        if(!status.equals("In_Process")) return  false;
        taskManagement.setTaskCompletedOn(DateAndTimeConfig.getCurrentDateAndTime());
        return true;
    }

    @Override
    public TaskManagement addActualTotalTime(Long assignedTaskId, String actualTotalTime)  throws SQLException{
        TaskManagement existingTaskManagement = taskManagementRepository.findById(assignedTaskId).orElseThrow(()-> new RuntimeException("task not found"));
        existingTaskManagement.setActualTotalTime(actualTotalTime);
        return taskManagementRepository.save(existingTaskManagement);

    }

    //passed name of project as id since id needs to be uniquef
    //need to refactor and ask delegatonMeasurablesAssociated
    //removed time shareId
    @Override
    public TaskManagement addAssignedTask(String taskOwnerUsername, String taskReceivedUsername, String activityName,
                                          String taskName, String projectCode, String projectName, LocalDate expectedDate,
                                          String expectedTime, String expectedTotalTime, String description, String taskAssignedOn,
                                          String actualTotalTime, String taskSeenOn, String taskCompletedOn, String taskAcceptedOn,
                                          String taskProcessOn, String taskApproveOn,
                                          String status)  throws SQLException{
//        , List<DelegationMeasurables> delegationMeasurablesAssociated
        User iptTskOwner = userRepository.findByUsername(taskOwnerUsername);
        if(iptTskOwner == null) throw new NotFoundException("username not exist");
        User iptTskReceiver = userRepository.findByUsername(taskReceivedUsername);
        if(iptTskReceiver == null) throw new NotFoundException("username not exist");
        Activity iptActivity = activityRepository.findByName(activityName);
        System.out.println(iptActivity);
        Project project;
        project = projectRepository.findByProjectCode(projectCode);
        if (project == null) throw new NotFoundException("projectCode not exist");

//        try {
//            iptActivity = activityRepository.findByName(activityName);
//        } catch (RuntimeException e) {
//            throw new RuntimeException("activity not found");
//        }
        TaskManagement assignedTaskManagement = new TaskManagement();
        assignedTaskManagement.setTaskOwnerUserID(iptTskOwner);
        assignedTaskManagement.setTaskReceivedUserID(iptTskReceiver);
      //  assignedTaskManagement.setTimeShareAssociated(timeShareAssociated);
        assignedTaskManagement.setActivityName(activityName);
        assignedTaskManagement.setTaskName(taskName);
        project.setName(projectName);
        assignedTaskManagement.setProjectName(project.getName());
        assignedTaskManagement.setProjectCode(project.getProjectCode());
        assignedTaskManagement.setExpectedDate(expectedDate);
        assignedTaskManagement.setExpectedTime(expectedTime);
        assignedTaskManagement.setExpectedTotalTime(expectedTotalTime);
        assignedTaskManagement.setDescription(description);
        assignedTaskManagement.setTaskAssignedOn(taskAssignedOn);
        assignedTaskManagement.setActualTotalTime(actualTotalTime);
        assignedTaskManagement.setTaskSeenOn(taskSeenOn);
        assignedTaskManagement.setTaskCompletedOn(taskCompletedOn);
        assignedTaskManagement.setTaskAcceptedOn(taskAcceptedOn);
        assignedTaskManagement.setTaskProcessedOn(taskProcessOn);
        assignedTaskManagement.setTasKApprovedOn(taskApproveOn);
        assignedTaskManagement.setStatus(status);
//        final TaskManagement saveAssignedTaskManagement = taskManagementRepository.save(assignedTaskManagement);
//        delegationMeasurablesAssociated.forEach(delegationMeasurable -> {
//
//            //delegationMeasurable.setId(Long.parseLong(delegationMeasurable.getFkMeasurableId().getName().replaceAll("[^0-9]", ""))); // Example: setting value
//            // Set other fields as required
//            delegationMeasurable.setActualMeasurableQuantity(delegationMeasurable.getActualMeasurableQuantity());
//            delegationMeasurable.setExpectedMeasurableQuantity(delegationMeasurable.getExpectedMeasurableQuantity());
//            delegationMeasurable.setMeasurableUnit(delegationMeasurable.getMeasurableUnit());
//            delegationMeasurablesRepository.save(delegationMeasurable);
//        });
        return taskManagementRepository.save(assignedTaskManagement);
    }



    @Override
    public TaskManagement updateModifiedTaskStatusAndDescription(String description, Long taskId) throws SQLException{
        TaskManagement existingTask = taskManagementRepository.findById(taskId).orElseThrow(()-> new RuntimeException("task not found"));
        existingTask.setDescription(description);
        existingTask.setStatus("revised");
        return taskManagementRepository.save(existingTask);
    }

    @Override
    public TaskManagement updateTaskManagementSeenOnTime(Long taskId)  throws SQLException{
        TaskManagement existingTaskManagement = taskManagementRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));
        String taskSeenOnTime = DateAndTimeConfig.getCurrentDateAndTime();
        existingTaskManagement.setTaskSeenOn(taskSeenOnTime);
        return taskManagementRepository.save(existingTaskManagement);
    }

    @Override
    public TaskManagement updateTaskManagementProcessedOnTime(Long taskId)  throws SQLException{
        TaskManagement existingTaskManagement = taskManagementRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));
        String taskCompletedTime = DateAndTimeConfig.getCurrentDateAndTime();
        existingTaskManagement.setStatus("In-Process");
        existingTaskManagement.setTaskProcessedOn(taskCompletedTime);
        return taskManagementRepository.save(existingTaskManagement);
    }

    @Override
    public TaskManagement updateTaskManagementApprovedOnTime(Long taskId)  throws SQLException{
        TaskManagement existingTaskManagement = taskManagementRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));
        String taskCompletedTime = DateAndTimeConfig.getCurrentDateAndTime();
        existingTaskManagement.setStatus("approved");
        existingTaskManagement.setTasKApprovedOn(taskCompletedTime);
        return taskManagementRepository.save(existingTaskManagement);
    }

    @Override
    public TaskManagement updateTaskManagementAcceptTime(Long taskId)  throws SQLException{
        TaskManagement existingTaskManagement = taskManagementRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));
        String taskCompletedTime = DateAndTimeConfig.getCurrentDateAndTime();
        existingTaskManagement.setStatus("Accepted");
        existingTaskManagement.setTaskAcceptedOn(taskCompletedTime);
        return taskManagementRepository.save(existingTaskManagement);
    }


    @Override
     public List<TaskManagement> getSendModificationTaskList(String  taskOwnerUserID, String status) throws SQLException{
        return taskManagementRepository.findByTaskOwnerUserIdAndStatus(taskOwnerUserID,status);
    }

    @Override
    public List<TaskManagement> getTaskList(String taskReceivedUsername) throws SQLException{
        return taskManagementRepository.findByTaskReceivedUserId(taskReceivedUsername);
    }

    @Override
    public List<TaskManagement> getDelegatedTaskList(String taskOwnerUsername) throws SQLException{
        return taskManagementRepository.findByTaskOwnerUserId(taskOwnerUsername);
    }

//    @Override
//    public Integer getTaskCountBasedOnUsernameAndStatus(String username){
//        return taskManagementRepository.CountByUserUsernameAndStatus(
//                username,"pending");
//    }

    @Override
    public Integer getPendingTaskCount(String username) throws SQLException{
        return taskManagementRepository.CountByUserUsernameAndStatus(
                 username,"Pending");
    }

    @Override
    public Integer getAcceptedTaskCount(String username) throws SQLException {
        return taskManagementRepository.CountByUserUsernameAndStatus( username, "Accepted");

    }

    @Override
    public Integer getApprovedTaskCount(String username) throws SQLException{
        return taskManagementRepository.CountByUserUsernameAndStatus( username, "Approved");
    }

    @Override
    public Integer getCompletedTaskCount(String username) throws SQLException{
        return taskManagementRepository.CountByUserUsernameAndStatus( username, "Completed");
    }

    @Override
    public Long getMaxDelegationTaskId() throws SQLException{
        Long maxId = taskManagementRepository.findMaxId();
        return maxId + 1;
    }

    @Override
    public String getActualTotalTime(Long assignedTaskId) throws SQLException{
        return taskManagementRepository.getActualTotalFromId(assignedTaskId);
    }


}
