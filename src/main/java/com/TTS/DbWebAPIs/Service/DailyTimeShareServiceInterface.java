package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;

import java.time.LocalDate;
import java.util.List;

public interface DailyTimeShareServiceInterface {

    List<DailyTimeShare> getDailyTimeShareList(String  username, String dateOfTimeShare);

   // DailyTimeShare addDailyTimeShare(DailyTimeShare dailyTimeShare, List<DailyTimeShareMeasurables> dailyTimeShareMeasurablesList);
    DailyTimeShare addDailyTimeShare(DailyTimeShare dailyTimeShare);
    //can be moved to user controller
    List<DailyTimeShare> getUserDTSReportDetails(String userId, String startDate, String endDate);
    //can be moved to project controller
    List<String> getProjectConsumedTime(String username, String startDate, String endDate);

    Long getMaxDailyTimeShareId();
}
