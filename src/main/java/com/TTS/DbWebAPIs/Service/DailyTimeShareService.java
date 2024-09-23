package com.TTS.DbWebAPIs.Service;


import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Repository.DailyTimeShareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DailyTimeShareService implements DailyTimeShareServiceInterface{

    DailyTimeShareRepository dailyTimeShareRepository;
    DailyTimeShareMeasurablesServiceInterface dailyTimeShareMeasurablesServiceInterface;



    public Optional<List<DailyTimeShare>> getDailyTimeShareList(Long userId, LocalDate dateOfTimeShare){
        return Optional.ofNullable(dailyTimeShareRepository.findAllByUsernameAndDateOfTimeShare(userId, dateOfTimeShare));
    }

    public DailyTimeShare addDailyTimeShare(DailyTimeShare dailyTimeShare, List<DailyTimeShareMeasurables> dailyTimeShareMeasurablesList){
        for(DailyTimeShareMeasurables dailyTimeShareMeasurable: dailyTimeShareMeasurablesList){
            dailyTimeShareMeasurablesServiceInterface.addDailyTimeShareMeasurables(dailyTimeShareMeasurable.getFkTimeShareId(), dailyTimeShareMeasurable.getFkMeasurablesID(),dailyTimeShareMeasurable.getMeasurableQuantity(),dailyTimeShareMeasurable.getMeasurableUnit());
        }
        return  dailyTimeShareRepository.save(dailyTimeShare);
    }

}
