package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface DailyTimeShareServiceInterface {

    List<DailyTimeShare> getDailyTimeShareList(String  username, LocalDate dateOfTimeShare) throws SQLException;

   // DailyTimeShare addDailyTimeShare(DailyTimeShare dailyTimeShare, List<DailyTimeShareMeasurables> dailyTimeShareMeasurablesList);
    DailyTimeShare addDailyTimeShare(DailyTimeShare dailyTimeShare) throws SQLException;
    //can be moved to user controller
    List<DailyTimeShare> getUserDTSReportDetails(String userId, LocalDate startDate, LocalDate endDate) throws SQLException;
    //can be moved to project controller
    List<String> getProjectConsumedTime(String username, LocalDate startDate, LocalDate endDate) throws SQLException;

    Long getMaxDailyTimeShareId() throws SQLException;
}
