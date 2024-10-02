package com.TTS.DbWebAPIs.DTO;

import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;

import java.util.List;

public class DailyTimeShareDTO {
    com.TTS.DbWebAPIs.Entity.DailyTimeShare dailyTimeShare;
    List<DailyTimeShareMeasurables> dailyTimeShareMeasurables;

    public DailyTimeShareDTO(com.TTS.DbWebAPIs.Entity.DailyTimeShare dailyTimeShare, List<DailyTimeShareMeasurables> dailyTimeShareMeasurables) {
        this.dailyTimeShare = dailyTimeShare;
        this.dailyTimeShareMeasurables = dailyTimeShareMeasurables;
    }

    public com.TTS.DbWebAPIs.Entity.DailyTimeShare getDailyTimeShare() {
        return dailyTimeShare;
    }

    public void setDailyTimeShare(com.TTS.DbWebAPIs.Entity.DailyTimeShare dailyTimeShare) {
        this.dailyTimeShare = dailyTimeShare;
    }

    public List<DailyTimeShareMeasurables> getDailyTimeShareMeasurables() {
        return dailyTimeShareMeasurables;
    }

    public void setDailyTimeShareMeasurables(List<DailyTimeShareMeasurables> dailyTimeShareMeasurables) {
        this.dailyTimeShareMeasurables = dailyTimeShareMeasurables;
    }
}
