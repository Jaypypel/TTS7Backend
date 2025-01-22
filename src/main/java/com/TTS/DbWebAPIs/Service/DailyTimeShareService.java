package com.TTS.DbWebAPIs.Service;


import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;

import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Repository.DailyTimeShareRepository;
import com.TTS.DbWebAPIs.Repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyTimeShareService implements DailyTimeShareServiceInterface{

  private final   DailyTimeShareRepository dailyTimeShareRepository;
  private  final   DailyTimeShareMeasurablesServiceInterface dailyTimeShareMeasurablesServiceInterface;
  private  final  UserRepository userRepository;
    //get a list of Daily TimeShare
    @Override
    public List<DailyTimeShare> getDailyTimeShareList(String username, LocalDate dateOfTimeShare) throws SQLException {
        return dailyTimeShareRepository.findAllByUserUsernameAndDateOfTimeShare(username, dateOfTimeShare);
    }

    //add a daily timeshare
    /*here need to check how can we pass dailyTimeshare Id to addDailyTimeShareMeasurables function*/
    @Transactional
    @Override
    public DailyTimeShare addDailyTimeShare(DailyTimeShare dailyTimeShare) throws SQLException{
//        for(DailyTimeShareMeasurables dailyTimeShareMeasurable: dailyTimeShareMeasurablesList){
//            dailyTimeShareMeasurablesServiceInterface.addDailyTimeShareMeasurables(dailyTimeShareMeasurable.getFkTimeShareId(), dailyTimeShareMeasurable.getFkMeasurablesID(),dailyTimeShareMeasurable.getMeasurableQuantity(),dailyTimeShareMeasurable.getMeasurableUnit());
//        }
        User user = userRepository.findByUsername(dailyTimeShare.getUser().getUsername());
        if(user==null){
            throw new RuntimeException("user not found");
        }
        dailyTimeShare.setUser(user);

        return  dailyTimeShareRepository.save(dailyTimeShare);
    }

    @Override
    public List<DailyTimeShare> getUserDTSReportDetails(String userId, LocalDate startDate, LocalDate endDate) throws SQLException{
        return dailyTimeShareRepository.getUserDTSReportDetails(userId,startDate,endDate);
    }

    @Override
    public List<String> getProjectConsumedTime(String  username, LocalDate startDate, LocalDate endDate) throws SQLException {
        return dailyTimeShareRepository.findByUserUsernameAndStartDateAndEndDate(username, startDate,endDate);
    }

    @Override
    public Long getMaxDailyTimeShareId() throws SQLException {
        return dailyTimeShareRepository.findMaxDailyTimeShareId();
    }


}
