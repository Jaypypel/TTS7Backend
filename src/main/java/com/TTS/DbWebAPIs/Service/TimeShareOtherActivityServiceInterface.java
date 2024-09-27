package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.OtherActivity;
import com.TTS.DbWebAPIs.Entity.TimeShareOtherActivity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface TimeShareOtherActivityServiceInterface {
    //should be name as addtimeshareOtheeractivity
    TimeShareOtherActivity addOtherActivity(Long userId, String activityName, LocalDateTime date, LocalTime startTime, LocalTime endTime, String timeDifference, String description, LocalTime createdOn);
}
