package com.TTS.DbWebAPIs.DTO;

import com.TTS.DbWebAPIs.Entity.TaskManagement;
import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Entity.TimeShareMeasurables;

import java.util.List;

public class TimeShareDTO {
    private Long taskHandlerId;
    private String date;
    private String startTime;
    private String endTime;

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Long getTaskHandlerId() {
        return taskHandlerId;
    }

    public void setTaskHandlerId(Long taskHandlerId) {
        this.taskHandlerId = taskHandlerId;
    }

    public String getTimeDifference() {
        return TimeDifference;
    }

    public void setTimeDifference(String timeDifference) {
        TimeDifference = timeDifference;
    }

    public static TimeShare getTimeShare() {
        return timeShare;
    }

    public static void setTimeShare(TimeShare timeShare) {
        TimeShareDTO.timeShare = timeShare;
    }

    private String TimeDifference;
    private String description;
    private String createdOn;
    private static TimeShare timeShare = new TimeShare();
    public static TimeShare convertToTimeShare(TimeShareDTO timeShareDTO){
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
