package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Entity.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ActivityServiceInterface {

    List<String> getActivityNames();
    //need to return only id and name as an object
    List<Activity> getActivityList(String userId);

    Activity addActivity(String username, String activityName, String createdOn);

    Integer getActivityCount(String username, LocalDate startDate, LocalDate endDate);

    Activity getActivity(String name);

    List<String> getActivityNamesByUsername(String name);
}
