package com.TTS.DbWebAPIs.DTO;

import com.TTS.DbWebAPIs.Entity.TaskManagement;
import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Entity.TimeShareMeasurables;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TimeShareDTO {
    private Long taskHandlerId;
    private LocalDate date;
    private String startTime;
    private String endTime;
    private String TimeDifference;
    private String description;
    private String createdOn;

    public static TimeShare convertToTimeShare(TimeShareDTO timeShareDTO){
        TimeShare timeShare = new TimeShare();
        TaskManagement taskManagement = new TaskManagement();
        taskManagement.setId(timeShareDTO.getTaskHandlerId());
        timeShare.setFkTaskManagementId(taskManagement);
        timeShare.setDateOfTimeShare(timeShareDTO.getDate());
        timeShare.setStartTime(timeShareDTO.getStartTime());
        timeShare.setEndTime(timeShareDTO.getEndTime());
        timeShare.setTimeDifference(timeShareDTO.getTimeDifference());
        timeShare.setDescription(timeShareDTO.getDescription());
        timeShare.setCreatedOn(timeShareDTO.getCreatedOn());
        return timeShare;
    }
}
