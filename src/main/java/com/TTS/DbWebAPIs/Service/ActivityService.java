package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Repository.ActivityRepository;
import com.TTS.DbWebAPIs.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public List<String> getActivityNames() {
        return activityRepository.getActivityNames();
    }

    //get a list of activity via userId
    @Override
    public List<Activity> getActivityList(Long userId) {
        return activityRepository.getActivityList(userId);
    }

    //add an activity
    @Override
    public Activity addActivity(Long userId, String activityName, LocalDate createdOn) {
        Activity inputActivity = new Activity();
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        inputActivity.setUser(user);
        inputActivity.setName(activityName);
   //     LocalDate activityCreatedOn = LocalDate.now();
        inputActivity.setCreatedOn(createdOn);
        return activityRepository .save(inputActivity);
    }

    //getActivityCount
    @Override
    public Integer getActivityCount(Long userId, LocalDate startDate, LocalDate endDate) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return activityRepository.ActivityCount(userId,startDate,endDate);
    }

    @Override
    public Activity getActivity(String name) {
        return activityRepository.findByName(name);
    }


}
