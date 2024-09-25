package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Entity.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ActivityServiceInterface {

    List<String> getActivityNames();

    List<Activity> getActivityList(String userId);

    Activity addActivity(Long userId, String activityName, LocalTime createdOn);

    Integer getActivityCount(User userId, String startDate, String endDate);
}
