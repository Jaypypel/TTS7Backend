package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Repository.DailyTimeShareMeasurblesRepository;
import com.TTS.DbWebAPIs.Repository.DailyTimeShareRepository;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.MeasurablesIdAndName;
import com.TTS.DbWebAPIs.Repository.MeasurablesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DailyTimeShareMeasurablesService implements DailyTimeShareMeasurablesServiceInterface {


   private final DailyTimeShareMeasurblesRepository dailyTimeShareMeasurblesRepository;
   private final MeasurablesRepository measurablesRepository;
   private final DailyTimeShareRepository dailyTimeShareRepository;

    @Override
    public DailyTimeShareMeasurables addDailyTimeShareMeasurables(Long timeShareId,
                                                                  Measurables mesrblId,
                                                                  Long mesrbQuantity,
                                                                  String mesrbUnit)  throws DatabaseException, NotFoundException {
        DailyTimeShareMeasurables dailyTimeShareMeasurables = new DailyTimeShareMeasurables();
        DailyTimeShare dailyTimeShare = dailyTimeShareRepository.findById(timeShareId).orElseThrow(() -> new NotFoundException("time share not found"));
        dailyTimeShareMeasurables.setDailyTimeShare(dailyTimeShare);
        dailyTimeShareMeasurables.setFkMeasurablesID(mesrblId);
        dailyTimeShareMeasurables.setMeasurableQuantity(mesrbQuantity);
        dailyTimeShareMeasurables.setMeasurableUnit(mesrbUnit);
        return dailyTimeShareMeasurblesRepository.save(dailyTimeShareMeasurables);
    }


    /*need to check whether getting list of dtsMeasurables or measurables since in the TTSDailyShareFragment Measurable model is different from than the db measurable model*/
    @Override
    public List<Measurables> getDailyTimeShareMeasurablesList(Long dtsId)  throws DatabaseException {
        return measurablesRepository.findMeasurablesById(dtsId);
    }
}
