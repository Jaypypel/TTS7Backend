package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.OtherActivity;
import com.TTS.DbWebAPIs.Entity.TimeShareOtherActivity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface TimeShareOtherActivityServiceInterface {
    //should be name as addtimeshareOtheeractivity
    TimeShareOtherActivity addOtherActivity(String username , String activityName, String date, String startTime,
                                            String endTime, String timeDifference, String description, String createdOn);
}
