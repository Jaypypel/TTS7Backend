package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.*;
import com.TTS.DbWebAPIs.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

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
    public List<String> getAcceptedTaskList(String taskReceivedUsername, String status) {
        return taskManagementRepository.findByUserUsernameAndStatus(taskReceivedUsername,status);
    }

    @Override
    public TaskManagement updateTaskManagementCompletedStatus(Long taskId) {
        TaskManagement existingTaskManagement = taskManagementRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));
        LocalTime taskCompletedTime = LocalTime.now();
        existingTaskManagement.setStatus("Completed");
        existingTaskManagement.setTaskCompletedOn(taskCompletedTime);
        return taskManagementRepository.save(existingTaskManagement);
    }

    @Override
    public TaskManagement addActualTotalTime(Long assignedTaskId, String actualTotalTime) {
        TaskManagement existingTaskManagement = taskManagementRepository.findById(assignedTaskId).orElseThrow(()-> new RuntimeException("task not found"));
        existingTaskManagement.setActualTotalTime(actualTotalTime);
        return existingTaskManagement;

    }

    //passed name of project as id since id needs to be uniquef
    //need to refactor and ask delegatonMeasurablesAssociated
    //removed time shareId
    @Override
    public TaskManagement addAssignedTask(String taskOwnerUsername, String taskReceivedUsername, String activityName,
                                          String taskName, String projectCode, String projectName, LocalDateTime expectedDate,
                                          LocalTime expectedTime, String expectedTotalTime, String description, LocalTime taskAssignedOn,
                                          String actualTotalTime, LocalTime taskSeenOn, LocalTime taskCompletedOn, LocalTime taskAcceptedOn,
                                          LocalTime taskProcessOn, LocalTime taskApproveOn,
                                          String status, List<DelegationMeasurables> delegationMeasurablesAssociated) {
        User iptTskOwner = userRepository.findByUsername(taskOwnerUsername);
        if(iptTskOwner.getUsername().isEmpty() || iptTskOwner.getEmail().isBlank()) new RuntimeException("task not assigned");
        User iptTskReceiver = userRepository.findByUsername(taskReceivedUsername);
        if(iptTskReceiver.getUsername().isBlank() || iptTskReceiver.getUsername().isEmpty()) throw new RuntimeException("task not received");
        Activity iptActivity = activityRepository.findByName(activityName);
        System.out.println(iptActivity);
        Project project;
        try {
            project = projectRepository.findByProjectCode(projectCode);
        } catch (Exception e){
            throw new RuntimeException("project not found");
        }
//        try {
//            iptActivity = activityRepository.findByName(activityName);
//        } catch (RuntimeException e) {
//            throw new RuntimeException("activity not found");
//        }
        TaskManagement assignedTaskManagement = new TaskManagement();
        assignedTaskManagement.setTaskOwnerUserID(iptTskOwner);
        assignedTaskManagement.setTaskReceivedUserID(iptTskReceiver);
      //  assignedTaskManagement.setTimeShareAssociated(timeShareAssociated);
        assignedTaskManagement.setActivityName(iptActivity.getName());
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
        final TaskManagement saveAssignedTaskManagement = taskManagementRepository.save(assignedTaskManagement);
        delegationMeasurablesAssociated.forEach(delegationMeasurable -> {
            delegationMeasurable.setFkTaskManagementID(saveAssignedTaskManagement);
            //delegationMeasurable.setId(Long.parseLong(delegationMeasurable.getFkMeasurableId().getName().replaceAll("[^0-9]", ""))); // Example: setting value
            // Set other fields as required
            delegationMeasurable.setActualMeasurableQuantity(delegationMeasurable.getActualMeasurableQuantity());
            delegationMeasurable.setExpectedMeasurableQuantity(delegationMeasurable.getExpectedMeasurableQuantity());
            delegationMeasurable.setMeasurableUnit(delegationMeasurable.getMeasurableUnit());
            delegationMeasurablesRepository.save(delegationMeasurable);
        });
        return saveAssignedTaskManagement;
    }

    @Override
    public TaskManagement updateModifiedTaskStatusAndDescription(String description, Long taskId) {
        TaskManagement existingTask = taskManagementRepository.findById(taskId).orElseThrow(()-> new RuntimeException("task not found"));
        existingTask.setDescription(description);
        existingTask.setStatus("revised");
        return existingTask;
    }

    @Override
    public TaskManagement updateTaskManagementSeenOnTime(Long taskId) {
        TaskManagement existingTaskManagement = taskManagementRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));
        LocalTime taskSeenOnTime = LocalTime.now();
        existingTaskManagement.setTaskSeenOn(taskSeenOnTime);
        return taskManagementRepository.save(existingTaskManagement);
    }

    @Override
    public TaskManagement updateTaskManagementProcessedOnTime(Long taskId) {
        TaskManagement existingTaskManagement = taskManagementRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));
        LocalTime taskCompletedTime = LocalTime.now();
        existingTaskManagement.setStatus("In-Process");
        existingTaskManagement.setTaskProcessedOn(taskCompletedTime);
        return taskManagementRepository.save(existingTaskManagement);
    }

    @Override
    public TaskManagement updateTaskManagementApprovedOnTime(Long taskId) {
        TaskManagement existingTaskManagement = taskManagementRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));
        LocalTime taskCompletedTime = LocalTime.now();
        existingTaskManagement.setStatus("approved");
        existingTaskManagement.setTasKApprovedOn(taskCompletedTime);
        return taskManagementRepository.save(existingTaskManagement);
    }

    @Override
    public TaskManagement updateTaskManagementAcceptTime(Long taskId) {
        TaskManagement existingTaskManagement = taskManagementRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));
        LocalTime taskCompletedTime = LocalTime.now();
        existingTaskManagement.setStatus("Accepted");
        existingTaskManagement.setTaskAcceptedOn(taskCompletedTime);
        return taskManagementRepository.save(existingTaskManagement);
    }


    @Override
public List<TaskManagement> getSendModificationTaskList(String  taskOwnerUserID, String status) {
        return taskManagementRepository.findByTaskOwnerUserIdAndStatus(taskOwnerUserID,status);
    }

    @Override
    public List<TaskManagement> getTaskList(String taskReceivedUsername) {
        return taskManagementRepository.findByTaskReceivedUserId(taskReceivedUsername);
    }

    @Override
    public List<TaskManagement> getDelegatedTaskList(String taskOwnerUsername) {
        return taskManagementRepository.findByTaskOwnerUserId(taskOwnerUsername);
    }

    @Override
    public Integer getPendingTaskCount(String username){
        return taskManagementRepository.CountByUserUsernameAndStatus(
                 username,"pending");
    }

    @Override
    public Integer getAcceptedTaskCount(String username) {
        return taskManagementRepository.CountByUserUsernameAndStatus( username, "accepted");

    }

    @Override
    public Integer getApprovedTaskCount(String username) {
        return taskManagementRepository.CountByUserUsernameAndStatus( username, "approved");
    }

    @Override
    public Integer getCompletedTaskCount(String username) {
        return taskManagementRepository.CountByUserUsernameAndStatus( username, "completed");
    }

    @Override
    public Long getMaxDelegationTaskId() {
        Long maxId = taskManagementRepository.findMaxId();
        return maxId + 1;
    }

    @Override
    public String getActualTotalTime(Long assignedTaskId) {
        return taskManagementRepository.getActualTotalFromId(assignedTaskId);
    }


}
