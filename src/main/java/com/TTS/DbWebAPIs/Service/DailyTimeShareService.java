package com.TTS.DbWebAPIs.Service;


import com.TTS.DbWebAPIs.DTO.DailyTimeShareDTO;
import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;

import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.InternalServerException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Exceptions.UserNotFoundException;
import com.TTS.DbWebAPIs.Repository.DailyTimeShareRepository;
import com.TTS.DbWebAPIs.Repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DailyTimeShareService implements DailyTimeShareServiceInterface{

  private final   DailyTimeShareRepository dailyTimeShareRepository;
//  private  final   DailyTimeShareMeasurablesServiceInterface dailyTimeShareMeasurablesServiceInterface;
  private  final  UserRepository userRepository;
    //get a list of Daily TimeShare



    @Cacheable(value = "dailyTimeShares")
    @Override
    public List<DailyTimeShareDTO> getDailyTimeShareList(String username, LocalDate dateOfTimeShare) throws DatabaseException {
         return dailyTimeShareRepository
                 .findAllByUserUsernameAndDateOfTimeShare(username, dateOfTimeShare)
                 .stream()
                 .map(DailyTimeShareDTO::mapDailyTimeSharetoDailyTimeShareDto)
                 .collect(Collectors.toList());
    }

    //add a daily timeshare
    /*here need to check how can we pass dailyTimeshare Id to addDailyTimeShareMeasurable function*/
    @CacheEvict(value = "dailyTimeShares",allEntries = true)
    @Transactional
    @Override
    public DailyTimeShare addDailyTimeShare(DailyTimeShare dailyTimeShare) throws UserNotFoundException,DatabaseException{
//        for(DailyTimeShareMeasurables dailyTimeShareMeasurable: dailyTimeShareMeasurablesList){
//            dailyTimeShareMeasurablesServiceInterface.addDailyTimeShareMeasurable(dailyTimeShareMeasurable.getFkTimeShareId(), dailyTimeShareMeasurable.getFkMeasurablesID(),dailyTimeShareMeasurable.getMeasurableQuantity(),dailyTimeShareMeasurable.getMeasurableUnit());
//        }
        User user = userRepository.findByUsername(dailyTimeShare.getUser().getUsername()).orElseThrow(() ->new UserNotFoundException("User not found"));
         dailyTimeShare.setUser(user);
        return  dailyTimeShareRepository.save(dailyTimeShare);
    }

    @Override
    public List<DailyTimeShare> getUserDTSReportDetails(String userId, LocalDate startDate, LocalDate endDate)  throws  DatabaseException{
        return dailyTimeShareRepository.getUserDTSReportDetails(userId,startDate,endDate);
    }

    @Override
    public List<String> getProjectConsumedTime(String  username, LocalDate startDate, LocalDate endDate) throws DatabaseException {
        return dailyTimeShareRepository.findByUserUsernameAndStartDateAndEndDate(username, startDate,endDate);
    }

    @Override
    public Long getMaxDailyTimeShareId() throws DatabaseException {
        return dailyTimeShareRepository.findMaxDailyTimeShareId();
    }


}
