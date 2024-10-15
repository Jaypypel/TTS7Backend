package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Entity.Task;
import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Repository.ActivityRepository;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.TaskName;
import com.TTS.DbWebAPIs.Repository.TaskRepository;
import com.TTS.DbWebAPIs.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements TaskServiceInterface{

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;



    @Override
    public List<String> getTaskNameList(String userId) {
        return taskRepository.findById(userId);
    }

    @Override
    public Integer getTaskCount(String username, LocalDate startDate, LocalDate endDate) {
        return taskRepository.findByUserIdAndStartDateAndEndDate(username,startDate,endDate);
    }

    @Override
    public Integer getTaskFreqeuncyCount(String username, LocalDate startDate, LocalDate endDate) {
        return taskRepository.findByIdAndStartDateAndEndDate(username,startDate,endDate);
    }

    @Override
    public Task addTask(String username, Long activtyId, String taskName, LocalTime createdOn) {
        User inputUser = userRepository.findByUsername(username);
        if(inputUser.getUsername().isBlank() || inputUser.getEmail().isEmpty()){
                throw  new RuntimeException("user not found ");
        }
        Activity inputActivity = activityRepository.findById(activtyId).orElseThrow(() -> new RuntimeException("activity not found"));
        Task task = new Task();
        task.setUser(inputUser);
        task.setActivityAssociated(inputActivity);
        task.setName(taskName);
        task.setCreatedOn(createdOn);
        return taskRepository.save(task);

    }


}
