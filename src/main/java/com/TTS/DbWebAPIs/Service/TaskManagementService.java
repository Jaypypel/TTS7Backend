package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.DTO.TaskManagementDTO;
import com.TTS.DbWebAPIs.Entity.*;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.InvalidAssignTaskRequestException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Exceptions.UserNotFoundException;
import com.TTS.DbWebAPIs.Repository.*;
import com.TTS.DbWebAPIs.Util.DateAndTimeConfig;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskManagementService implements TaskManagementServiceInterface{

    private  final TaskManagementRepository taskManagementRepository;
    private final UserRepository userRepository;


    /*the function is  used in existing tts app for the receivedModfifcationtAskList,*/

    @Cacheable("acceptedTasks")
    @Override
    public List<TaskManagement> getAcceptedTaskList(String taskReceivedUsername, String status) throws DatabaseException {
        return taskManagementRepository.findByUserUsernameAndStatus(taskReceivedUsername,status);
    }

//    @Override
//    public TaskManagement updateTaskManagementAssociatedTimeByStatus(Long taskId, String status) {
//        TaskManagement existingTaskManagement = taskManagementRepository
//                .findById(taskId)
//                .orElseThrow(
//                        () ->
//                        new NotFoundException("task not found")
//                );
//        String taskCompletedTime = DateAndTimeConfig.getCurrentDateAndTime();
//        existingTaskManagement.setStatus(status);
//        existingTaskManagement.setTaskProcessedOn(taskCompletedTime);
//        return taskManagementRepository.save(existingTaskManagement);
//    }
//
@CacheEvict(value = { "acceptedTasks","modifiedTasks","tasks","delegatedTasks"}, allEntries = true)
    @Override
    public TaskManagement updateTaskManagementStatus(Long taskId, String status)  throws NotFoundException, DatabaseException{
        TaskManagement existingTaskManagement = taskManagementRepository
                .findById(taskId).orElseThrow(() -> new NotFoundException("task not found"));
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


    @CacheEvict(value = { "acceptedTasks","modifiedTasks","tasks","delegatedTasks"}, allEntries = true)
    @Override
    public TaskManagement addActualTotalTime(Long assignedTaskId, String actualTotalTime) throws  DatabaseException, NotFoundException {
        TaskManagement existingTaskManagement = taskManagementRepository.findById(assignedTaskId).orElseThrow(()-> new NotFoundException("task not found"));
        existingTaskManagement.setActualTotalTime(actualTotalTime);
        return taskManagementRepository.save(existingTaskManagement);

    }

    //passed name of project as id since id needs to be uniquef
    //need to refactor and ask delegatonMeasurablesAssociated
    //removed time shareId

    @CacheEvict(value = { "acceptedTasks","modifiedTasks","tasks","delegatedTasks"}, allEntries = true)
    @Transactional
    @Override
    public TaskManagement addAssignedTask(TaskManagementDTO taskManagementDTO)  throws UserNotFoundException, InvalidAssignTaskRequestException, DatabaseException {
//        , List<DelegationMeasurables> delegationMeasurablesAssociated
             User userWhoReceiveTask =  userRepository
                    .findByUsername(taskManagementDTO.getTaskReceivedUserID())
                    .orElseThrow(
                            () -> new UserNotFoundException("User who you want to assign task is not found by username : "+
                                    taskManagementDTO.getTaskReceivedUserID()));
            User taskOwner = userRepository
                  .findByUsername(taskManagementDTO.getTaskOwnerUserID())
                  .orElseThrow(() -> new UserNotFoundException("User who is assigning task is not found by username : "+taskManagementDTO.getTaskOwnerUserID()));


            TaskManagement assignedTaskManagement = TaskManagementDTO.convertToTaskmanagmentEntity(taskManagementDTO);
            if(assignedTaskManagement.getExpectedDate().isBefore(LocalDate.now())) throw new InvalidAssignTaskRequestException("Expected date "+taskManagementDTO.getExpectedDate()+" can be past date");
            if (LocalTime.parse(assignedTaskManagement.getExpectedTime(), DateTimeFormatter.ofPattern("h:mma")).isBefore(LocalTime.now())) throw new InvalidAssignTaskRequestException("Expected time "+taskManagementDTO.getExpectedTime()+ " can not be past time");

            assignedTaskManagement.setTaskReceivedUserID(userWhoReceiveTask);
            assignedTaskManagement
                    .setTaskOwnerUserID(taskOwner);
        return taskManagementRepository.save(assignedTaskManagement);
    }


    @CacheEvict(value = { "acceptedTasks","modifiedTasks","tasks","delegatedTasks"}, allEntries = true)
    @Override
    public TaskManagement updateModifiedTaskStatusAndDescription(String description, Long taskId) throws DatabaseException, NotFoundException{
        TaskManagement existingTask = taskManagementRepository.findById(taskId).orElseThrow(()-> new NotFoundException("task not found"));
        existingTask.setDescription(description);
        existingTask.setStatus("revised");
        return taskManagementRepository.save(existingTask);
    }

    @CacheEvict(value = { "acceptedTasks","modifiedTasks","tasks","delegatedTasks"}, allEntries = true)
    @Override
    public TaskManagement updateTaskManagementSeenOnTime(Long taskId)  throws DatabaseException,NotFoundException{
        TaskManagement existingTaskManagement = taskManagementRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));
        String taskSeenOnTime = DateAndTimeConfig.getCurrentDateAndTime();
        existingTaskManagement.setTaskSeenOn(taskSeenOnTime);
        return taskManagementRepository.save(existingTaskManagement);
    }

//    @Override
//    public TaskManagement updateTaskManagementProcessedOnTime(Long taskId)  throws DatabaseException, NotFoundException{
//
//    }
//
//    @Override
//    public TaskManagement updateTaskManagementApprovedOnTime(Long taskId)  throws DatabaseException, NotFoundException{
//        TaskManagement existingTaskManagement = taskManagementRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));
//        String taskCompletedTime = DateAndTimeConfig.getCurrentDateAndTime();
//        existingTaskManagement.setStatus("Approved");
//        existingTaskManagement.setTasKApprovedOn(taskCompletedTime);
//        return taskManagementRepository.save(existingTaskManagement);
//    }
//
//    @Override
//    public TaskManagement updateTaskManagementAcceptTime(Long taskId)  throws DatabaseException, NotFoundException{
//        TaskManagement existingTaskManagement = taskManagementRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));
//        String taskCompletedTime = DateAndTimeConfig.getCurrentDateAndTime();
//        existingTaskManagement.setStatus("Accepted");
//        existingTaskManagement.setTaskAcceptedOn(taskCompletedTime);
//        return taskManagementRepository.save(existingTaskManagement);
//    }

    @Cacheable(value = "modifiedTasks")
    @Override
     public List<TaskManagement> getSendModificationTaskList(String  taskOwnerUserID, String status) throws DatabaseException, NotFoundException{
        return taskManagementRepository.findByTaskOwnerUserIdAndStatus(taskOwnerUserID,status);
    }

    @Cacheable(value = "tasks")
    @Override
    public List<TaskManagement> getTaskList(String taskReceivedUsername) throws DatabaseException, NotFoundException{
        return taskManagementRepository.findByTaskReceivedUserId(taskReceivedUsername);
    }

    @Cacheable(value = "delegatedTasks")
    @Override
    public List<TaskManagement> getDelegatedTaskList(String taskOwnerUsername) throws DatabaseException, NotFoundException {
        return taskManagementRepository.findByTaskOwnerUserId(taskOwnerUsername);
    }

    @Override
    public Integer getTaskCountByStatusAndUsername(String username, String status) {
        return taskManagementRepository.CountByUserUsernameAndStatus(username,status);
    }


//    @Override
//    public Integer getTaskCountBasedOnUsernameAndStatus(String username){
//        return taskManagementRepository.CountByUserUsernameAndStatus(
//                username,"pending");
//    }

//    @Override
//    public Integer getPendingTaskCount(String username) throws DatabaseException,  NotFoundException{
//        return taskManagementRepository.CountByUserUsernameAndStatus(
//                 username,"Pending");
//    }
//
//    @Override
//    public Integer getAcceptedTaskCount(String username) throws DatabaseException, NotFoundException {
//        return taskManagementRepository.CountByUserUsernameAndStatus( username, "Accepted");
//
//    }
//
//    @Override
//    public Integer getApprovedTaskCount(String username) throws DatabaseException, NotFoundException {
//        return taskManagementRepository.CountByUserUsernameAndStatus( username, "Approved");
//    }
//
//    @Override
//    public Integer getCompletedTaskCount(String username) throws DatabaseException, NotFoundException{
//        return taskManagementRepository.CountByUserUsernameAndStatus( username, "Completed");
//    }

    @Override
    public Long getMaxDelegationTaskId() throws DatabaseException, NotFoundException{
        Long maxId = taskManagementRepository.findMaxId();
        return maxId + 1;
    }

    @Override
    public String getActualTotalTime(Long assignedTaskId) throws DatabaseException, NotFoundException{
        return taskManagementRepository.getActualTotalFromId(assignedTaskId);
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

}
