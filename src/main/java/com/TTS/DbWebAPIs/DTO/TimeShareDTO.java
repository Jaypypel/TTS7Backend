package com.TTS.DbWebAPIs.DTO;

import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Entity.TimeShareMeasurables;

import java.util.List;

public class TimeShareDTO {
    TimeShare timeShare;
    List<TimeShareMeasurables> timeShareMeasurablesList;

    public TimeShareDTO(TimeShare timeShare, List<TimeShareMeasurables> timeShareMeasurablesList) {
        this.timeShare = timeShare;
        this.timeShareMeasurablesList = timeShareMeasurablesList;
    }

    @Override
    public String toString() {
        return "TimeShareDTO{" +
                "timeShare=" + timeShare +
                ", timeShareMeasurablesList=" + timeShareMeasurablesList +
                '}';
    }

    public TimeShare getTimeShare() {
        return timeShare;
    }

    public void setTimeShare(TimeShare timeShare) {
        this.timeShare = timeShare;
    }

    public List<TimeShareMeasurables> getTimeShareMeasurablesList() {
        return timeShareMeasurablesList;
    }

    public void setTimeShareMeasurablesList(List<TimeShareMeasurables> timeShareMeasurablesList) {
        this.timeShareMeasurablesList = timeShareMeasurablesList;
    }
}
