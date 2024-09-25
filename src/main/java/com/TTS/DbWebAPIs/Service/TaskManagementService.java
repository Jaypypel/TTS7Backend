package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.*;
import com.TTS.DbWebAPIs.Repository.ActivityRepository;
import com.TTS.DbWebAPIs.Repository.ProjectRepository;
import com.TTS.DbWebAPIs.Repository.TaskManagementRepository;
import com.TTS.DbWebAPIs.Repository.UserRepository;
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

    @Override
    public List<TaskManagement> getAcceptedTaskList(Long truId, String status) {
        return taskManagementRepository.findByTaskReceivedUserIDAndStatus(truId,status);
    }

    @Override
    public TaskManagement updateTaskManagementCompletedStatus(Long taskId) {
        TaskManagement existingTaskManagement = taskManagementRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));
        LocalTime taskCompletedTime = LocalTime.now();
        existingTaskManagement.setStatus("Completed");
        existingTaskManagement.setTaskCompletedOn(taskCompletedTime);
        return taskManagementRepository.save(existingTaskManagement);
    }
    //passed name of project as id since id needs to be unique
    @Override
    public TaskManagement addAssignedTask(Long taskOwnerUserID, Long taskReceivedUserID, TimeShare timeShareAssociated, String activityName,
                                          String taskName, String projectId, String projectName, LocalDateTime expectedDate,
                                          LocalTime expectedTime, String expectedTotalTime, String description, LocalTime taskAssignedOn,
                                          String actualTotalTime, LocalTime taskSeenOn, LocalTime taskCompletedOn, LocalTime taskAcceptedOn,
                                          String status, List<DelegationMeasurables> delegationMeasurablesAssociated) {
        User iptTskOwner = userRepository.findById(taskOwnerUserID).orElseThrow(()->new RuntimeException("task not assigned"));
        User iptTskReceiver = userRepository.findById(taskReceivedUserID).orElseThrow(()->new RuntimeException("task not received"));
        Activity iptActivity;
        Project iptProject;
        try {
            iptActivity = activityRepository.findByName(activityName);
        } catch (RuntimeException e) {
            throw new RuntimeException("activity not found");
        }
        try {
             iptProject = projectRepository.findByName(projectId);
        } catch (RuntimeException e) {
            throw new RuntimeException("project not found");
        }
        TaskManagement assignedTaskManagement = new TaskManagement();
        assignedTaskManagement.setTaskOwnerUserID(iptTskOwner);
        assignedTaskManagement.setTaskReceivedUserID(iptTskReceiver);
        assignedTaskManagement.setTimeShareAssociated(timeShareAssociated);
        assignedTaskManagement.setActivityName(iptActivity.getName());
        assignedTaskManagement.setTaskName(taskName);
        iptProject.setName(projectName);
        assignedTaskManagement.setProjectName(iptProject.getName());
        assignedTaskManagement.setExpectedDate(expectedDate);
        assignedTaskManagement.setExpectedTime(expectedTime);
        assignedTaskManagement.setExpectedTotalTime(expectedTotalTime);
        assignedTaskManagement.setDescription(description);
        assignedTaskManagement.setTaskAssignedOn(taskAssignedOn);
        assignedTaskManagement.setActualTotalTime(actualTotalTime);
        assignedTaskManagement.setTaskSeenOn(taskSeenOn);
        assignedTaskManagement.setTaskCompletedOn(taskCompletedOn);
        assignedTaskManagement.setTaskAcceptedOn(taskAcceptedOn);
        assignedTaskManagement.setStatus(status);
        final TaskManagement saveAssignedTaskManagement = taskManagementRepository.save(assignedTaskManagement);
        delegationMeasurablesAssociated.forEach(delegationMeasurable -> {
            delegationMeasurable.setFkTaskManagementID(saveAssignedTaskManagement);
            delegationMeasurable.setId(Long.parseLong(delegationMeasurable.getFkMeasurableId().getName().replaceAll("[^0-9]", ""))); // Example: setting value
            // Set other fields as required
        });
    }

    @Override
    public TaskManagement updateTaskManagementSeenOnTime(Long taskId) {
        TaskManagement existingTaskManagement = taskManagementRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));
        LocalTime taskSeenOnTime = LocalTime.now();
        existingTaskManagement.setTaskCompletedOn(taskSeenOnTime);
        return taskManagementRepository.save(existingTaskManagement);
    }

    @Override
    public TaskManagement updateTaskManagementProcessedOnTime(Long taskId) {
        TaskManagement existingTaskManagement = taskManagementRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));
        LocalTime taskCompletedTime = LocalTime.now();
        existingTaskManagement.setStatus("In-Process");
        existingTaskManagement.setTaskCompletedOn(taskCompletedTime);
        return taskManagementRepository.save(existingTaskManagement);
    }

    @Override
    public TaskManagement updateTaskManagementApprovedOnTime(Long taskId) {
        TaskManagement existingTaskManagement = taskManagementRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));
        LocalTime taskCompletedTime = LocalTime.now();
        existingTaskManagement.setStatus("approved");
        existingTaskManagement.setTaskCompletedOn(taskCompletedTime);
        return taskManagementRepository.save(existingTaskManagement);
    }

    @Override
    public TaskManagement updateTaskManagementAcceptTime(Long taskId) {
        TaskManagement existingTaskManagement = taskManagementRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found"));
        LocalTime taskCompletedTime = LocalTime.now();
        existingTaskManagement.setStatus("Accepted");
        existingTaskManagement.setTaskCompletedOn(taskCompletedTime);
        return taskManagementRepository.save(existingTaskManagement);
    }


    @Override
    public List<TaskManagement> getSendModificationTaskList(Long touId, String status) {
        return taskManagementRepository.findByTaskOwnerUserIDAndStatus(touId,status);
    }

    @Override
    public List<TaskManagement> getTaskList(Long truId) {
        return taskManagementRepository.findByTasKReceivedUserId(truId);
    }

    @Override
    public List<TaskManagement> getDelegatedTaskList(Long touId) {
        return taskManagementRepository.findByTasKOwnerUserId(touId);
    }

    @Override
    public Integer getPendingTaskCount(Long userId){
        return taskManagementRepository.findByTaskReceivedUserIDAndStatus(userId,"pending").size();
    }

    @Override
    public Integer getAcceptedTaskCount(Long userId) {
        return taskManagementRepository.findByTaskReceivedUserIDAndStatus(userId, "In-Process").size();
    }

    @Override
    public Integer getApprovedTaskCount(Long userId) {
        return taskManagementRepository.findByTaskReceivedUserIDAndStatus(userId, "approved").size();
    }

    @Override
    public Integer getCompletedTaskCount(Long userId) {
        return taskManagementRepository.findByTaskReceivedUserIDAndStatus(userId, "completed").size();
    }


}
