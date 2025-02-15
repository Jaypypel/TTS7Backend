package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.InternalServerException;
import com.TTS.DbWebAPIs.Exceptions.UserNotFoundException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface DailyTimeShareServiceInterface {

    List<DailyTimeShare> getDailyTimeShareList(String  username, LocalDate dateOfTimeShare)  throws  DatabaseException, InternalServerException;

   // DailyTimeShare addDailyTimeShare(DailyTimeShare dailyTimeShare, List<DailyTimeShareMeasurables> dailyTimeShareMeasurablesList);
    DailyTimeShare addDailyTimeShare(DailyTimeShare dailyTimeShare) throws UserNotFoundException, DatabaseException;
    //can be moved to user controller
    List<DailyTimeShare> getUserDTSReportDetails(String userId, LocalDate startDate, LocalDate endDate) throws   DatabaseException, InternalServerException;
    //can be moved to project controller
    List<String> getProjectConsumedTime(String username, LocalDate startDate, LocalDate endDate) throws  DatabaseException, InternalServerException;

    Long getMaxDailyTimeShareId() throws SQLException;
}
