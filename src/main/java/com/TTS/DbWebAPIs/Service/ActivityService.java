package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Repository.ActivityRepository;
import com.TTS.DbWebAPIs.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService implements ActivityServiceInterface{

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;


    //get a list of names of activity
    @Override
    public List<String> getActivityNames() throws SQLException {
        return activityRepository.getActivityNames();
    }

    //    @Override
//    public Page<String> getActivityNames(Pageable pageable) {
//        return activityRepository.getActivityNames(pageable);
//    }



    //get a list of activity via userId
    @Override
    public List<Activity> getActivityList(String username) throws SQLException {
        return activityRepository.getActivityList(username);
    }

    //add an activity
    @Override
    public Activity addActivity(String username, String activityName, String createdOn) throws SQLException {
        Activity inputActivity = new Activity();
        User user = userRepository.findByUsername(username);
        if(user.getUsername().isEmpty() || user.getUsername().isBlank()){
            throw new RuntimeException("username not found");
        }
        inputActivity.setUser(user);
        inputActivity.setName(activityName);
        inputActivity.setCreatedOn(createdOn);
        return activityRepository .save(inputActivity);
    }

    //getActivityCount
    @Override
    public Integer getActivityCount(String username, LocalDate startDate, LocalDate endDate) throws SQLException {
        User user = userRepository.findByUsername(username);
        if(user.getUsername().isBlank() || user.getUsername().isEmpty()){
            throw new RuntimeException("User not found");
        }
        return activityRepository.ActivityCount(username,startDate,endDate);
    }

    @Override
    public Activity getActivity(String name) throws SQLException {
        return activityRepository.findByName(name);
    }

    @Override
    public List<String> getActivityNamesByUsername(String username) throws SQLException {
        User user = userRepository.findByUsername(username);
        if(user.getUsername().isBlank() || user.getUsername().isEmpty()){
            throw new RuntimeException("User not found");
        }
      return   activityRepository.getActivityNamesbyUserName(username);
    }


}
