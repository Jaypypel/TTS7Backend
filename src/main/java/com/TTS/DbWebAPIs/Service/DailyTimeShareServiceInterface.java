package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyTimeShareServiceInterface {

    Optional<List<DailyTimeShare>> getDailyTimeShareList(Long userId, LocalDate dateOfTimeShare);

    DailyTimeShare addDailyTimeShare(DailyTimeShare dailyTimeShare, List<DailyTimeShareMeasurables> dailyTimeShareMeasurablesList);

    List<DailyTimeShare> getUserDTSReportDetails(Long userId, LocalDate startDate, LocalDate endDate);

    List<String> getProjectConsumedTime(Long userID, LocalDate startDate, LocalDate endDate);

    Integer getMaxDailyTimeShareId();
}
