package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;

import java.time.LocalDate;
import java.util.List;

public interface DailyTimeShareServiceInterface {

    List<DailyTimeShare> getDailyTimeShareList(Long userId, LocalDate dateOfTimeShare);

    DailyTimeShare addDailyTimeShare(DailyTimeShare dailyTimeShare, List<DailyTimeShareMeasurables> dailyTimeShareMeasurablesList);
    //can be moved to user controller
    List<DailyTimeShare> getUserDTSReportDetails(Long userId, LocalDate startDate, LocalDate endDate);
    //can be moved to project controller
    List<String> getProjectConsumedTime(Long userID, LocalDate startDate, LocalDate endDate);

    Integer getMaxDailyTimeShareId();
}
