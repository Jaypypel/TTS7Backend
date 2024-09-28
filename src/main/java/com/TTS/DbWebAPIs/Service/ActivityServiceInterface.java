package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Entity.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ActivityServiceInterface {

    List<String> getActivityNames();
    //need to return only id and name as an object
    List<Activity> getActivityList(Long userId);

    Activity addActivity(Long userId, String activityName);

    Integer getActivityCount(Long userId, String startDate, String endDate);
}
