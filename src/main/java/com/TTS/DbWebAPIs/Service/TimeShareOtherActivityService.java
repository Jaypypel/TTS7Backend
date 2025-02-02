package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.TimeShareOtherActivity;
import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Repository.TimeShareOtherActivityRepository;
import com.TTS.DbWebAPIs.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TimeShareOtherActivityService implements TimeShareOtherActivityServiceInterface{

    private  final UserRepository userRepository;
    private  final TimeShareOtherActivityRepository timeShareOtherActivityRepository;
    @Override
    public TimeShareOtherActivity addOtherActivity(String username, String activityName, String date, String startTime, String endTime, String timeDifference, String description, String createdOn) {
        User user = userRepository.findByUsername(username);
        if(user.getUsername().isEmpty()){
            throw new RuntimeException("username not found");
        }
        TimeShareOtherActivity timeShareOtherActivity = new TimeShareOtherActivity();
        timeShareOtherActivity.setUser(user);
        timeShareOtherActivity.setActivity(activityName);
        timeShareOtherActivity.setDate(date);
        timeShareOtherActivity.setStartTime(startTime);
        timeShareOtherActivity.setEndTime(endTime);
        timeShareOtherActivity.setDescription(description);
        timeShareOtherActivity.setTimeDifference(timeDifference);
        timeShareOtherActivity.setCreatedOn(createdOn);
        return timeShareOtherActivityRepository.save(timeShareOtherActivity);
    }
}
