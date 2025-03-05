package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Entity.Task;
import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Exceptions.UserNotFoundException;
import com.TTS.DbWebAPIs.Repository.ActivityRepository;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.TaskName;
import com.TTS.DbWebAPIs.Repository.TaskRepository;
import com.TTS.DbWebAPIs.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements TaskServiceInterface{

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;


    @Cacheable(value = "tasks")
    @Override
    public List<String> getTaskNameList(String userId)  throws DatabaseException {
        return taskRepository.findById(userId);
    }

    @Override
    public Integer getTaskCount(String username, LocalDate startDate, LocalDate endDate)  throws DatabaseException{
        return taskRepository.findByUserIdAndStartDateAndEndDate(username,startDate,endDate);
    }

    @Override
    public Integer getTaskFreqeuncyCount(String username, LocalDate startDate, LocalDate endDate)   throws DatabaseException{
        return taskRepository.findByIdAndStartDateAndEndDate(username,startDate,endDate);
    }


    @CacheEvict(value = {"tasks,distinctTasks"},allEntries = true)
    @Override
    public Task addTask(String username, Long activtyId, String taskName, String createdOn)   throws DatabaseException, NotFoundException, UserNotFoundException {
        Activity inputActivity = activityRepository.findById(activtyId).orElseThrow(() -> new NotFoundException("activity not found"));
        User user =  userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("Username not found"));
        Task task = new Task();
        task.setActivityAssociated(inputActivity);
        task.setUser(user);
        task.setName(taskName);
        task.setCreatedOn(createdOn);
        return taskRepository.save(task);

    }


    @Cacheable(value = "distinctTasks")
    @Override
    public List<String> getTaskNames() throws DatabaseException  {
        return taskRepository.findAllDistinctName();
    }


}
