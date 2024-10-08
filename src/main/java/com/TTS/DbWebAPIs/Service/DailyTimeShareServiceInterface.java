package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;

import java.time.LocalDate;
import java.util.List;

public interface DailyTimeShareServiceInterface {

    List<DailyTimeShare> getDailyTimeShareList(String  username, LocalDate dateOfTimeShare);

    DailyTimeShare addDailyTimeShare(DailyTimeShare dailyTimeShare, List<DailyTimeShareMeasurables> dailyTimeShareMeasurablesList);
    //can be moved to user controller
    List<DailyTimeShare> getUserDTSReportDetails(String userId, LocalDate startDate, LocalDate endDate);
    //can be moved to project controller
    List<String> getProjectConsumedTime(String username, LocalDate startDate, LocalDate endDate);

    Long getMaxDailyTimeShareId();
}
