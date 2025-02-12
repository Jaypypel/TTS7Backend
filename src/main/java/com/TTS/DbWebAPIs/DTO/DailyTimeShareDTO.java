package com.TTS.DbWebAPIs.DTO;

import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DailyTimeShareDTO {

    private String dateOfTimeShare;
    private String projectCode;
    private String projectName;
    private String activityName;
    private String taskName;
    private String startTime;
    private String endTime;
    private String timeDifference;
    private String description;
    private String createdOn;
    private String username ;

    public static DailyTimeShare mapDailyTimeShareDTOtoDailyTimeShare(DailyTimeShareDTO dailyTimeShareDTO){
        DailyTimeShare dailyTimeShare = new DailyTimeShare();
        dailyTimeShare.setDateOfTimeShare(LocalDate.parse(dailyTimeShareDTO.getDateOfTimeShare()));
        return dailyTimeShare;
    }
}
