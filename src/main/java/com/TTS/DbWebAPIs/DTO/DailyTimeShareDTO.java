package com.TTS.DbWebAPIs.DTO;

import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DailyTimeShareDTO {

    private Long id;
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

    public static DailyTimeShareDTO mapDailyTimeSharetoDailyTimeShareDto(DailyTimeShare dailyTimeShare){
        DailyTimeShareDTO dailyTimeShareDTO =  new DailyTimeShareDTO();
        dailyTimeShareDTO.setId(dailyTimeShare.getId());
        dailyTimeShareDTO.setDateOfTimeShare(String.valueOf(dailyTimeShare.getDateOfTimeShare()));
        dailyTimeShareDTO.setProjectCode(dailyTimeShare.getProjectCode());
        dailyTimeShareDTO.setProjectName(dailyTimeShare.getProjectName());
        dailyTimeShareDTO.setActivityName(dailyTimeShare.getActivityName());
        dailyTimeShareDTO.setTaskName(dailyTimeShare.getTaskName());
        dailyTimeShareDTO.setStartTime(dailyTimeShare.getStartTime());
        dailyTimeShareDTO.setEndTime(dailyTimeShare.getEndTime());
        dailyTimeShareDTO.setDescription(dailyTimeShare.getDescription());
        dailyTimeShareDTO.setUsername(dailyTimeShare.getUser().getUsername());
        return dailyTimeShareDTO;
    }
}
