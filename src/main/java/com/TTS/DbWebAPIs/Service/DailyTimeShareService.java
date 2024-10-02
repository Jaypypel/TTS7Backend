package com.TTS.DbWebAPIs.Service;


import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;

import com.TTS.DbWebAPIs.Repository.DailyTimeShareRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyTimeShareService implements DailyTimeShareServiceInterface{

  private final   DailyTimeShareRepository dailyTimeShareRepository;
  private  final   DailyTimeShareMeasurablesServiceInterface dailyTimeShareMeasurablesServiceInterface;

    //get a list of Daily TimeShare
    @Override
    public List<DailyTimeShare> getDailyTimeShareList(Long userId, LocalDate dateOfTimeShare){
        return dailyTimeShareRepository.findAllByIdAndDateOfTimeShare(userId, dateOfTimeShare);
    }

    //add a daily timeshare
    /*here need to check how can we pass dailyTimeshare Id to addDailyTimeShareMeasurables function*/
    @Transactional
    @Override
    public DailyTimeShare addDailyTimeShare(DailyTimeShare dailyTimeShare, List<DailyTimeShareMeasurables> dailyTimeShareMeasurablesList){
        for(DailyTimeShareMeasurables dailyTimeShareMeasurable: dailyTimeShareMeasurablesList){
            dailyTimeShareMeasurablesServiceInterface.addDailyTimeShareMeasurables(dailyTimeShareMeasurable.getFkTimeShareId(), dailyTimeShareMeasurable.getFkMeasurablesID(),dailyTimeShareMeasurable.getMeasurableQuantity(),dailyTimeShareMeasurable.getMeasurableUnit());
        }
        return  dailyTimeShareRepository.save(dailyTimeShare);
    }

    @Override
    public List<DailyTimeShare> getUserDTSReportDetails(Long userId, LocalDate startDate, LocalDate endDate) {
        return dailyTimeShareRepository.getUserDTSReportDetails(userId,startDate,endDate);
    }

    @Override
    public List<String> getProjectConsumedTime(Long userID, LocalDate startDate, LocalDate endDate) {
        return dailyTimeShareRepository.findTimeDiffByIdAndDateRange(userID, startDate,endDate);
    }

    @Override
    public Integer getMaxDailyTimeShareId() {
        return dailyTimeShareRepository.findMaxDailyTimeShareId();
    }


}
