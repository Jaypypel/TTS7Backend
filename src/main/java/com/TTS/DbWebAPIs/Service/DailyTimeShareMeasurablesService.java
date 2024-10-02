package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Repository.DailyTimeShareMeasurblesRepository;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.MeasurablesIdAndName;
import com.TTS.DbWebAPIs.Repository.MeasurablesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyTimeShareMeasurablesService implements DailyTimeShareMeasurablesServiceInterface {


   private final DailyTimeShareMeasurblesRepository dailyTimeShareMeasurblesRepository;
   private final MeasurablesRepository measurablesRepository;

    @Override
    public DailyTimeShareMeasurables addDailyTimeShareMeasurables(TimeShare timeShareId, Measurables mesrblId, Long mesrbQuantity, String mesrbUnit) {
        DailyTimeShareMeasurables dailyTimeShareMeasurables = new DailyTimeShareMeasurables();
        dailyTimeShareMeasurables.setFkTimeShareId(timeShareId);
        dailyTimeShareMeasurables.setFkMeasurablesID(mesrblId);
        dailyTimeShareMeasurables.setMeasurableQuantity(mesrbQuantity);
        dailyTimeShareMeasurables.setMeasurableUnit(mesrbUnit);
        return dailyTimeShareMeasurblesRepository.save(dailyTimeShareMeasurables);
    }
//
//    @Override
//    public List<DailyTimeShareMeasurables> getDailyTimeShareMeasurablesList(Long dtsId) {
//        List<DailyTimeShareMeasurables>  dailyTimeShareMeasurables = new ArrayList<>();
//        DailyTimeShareMeasurables dailyTimeShareMeasurable;
//        while(dailyTimeShareMeasurblesRepository.getDailyTimeShareMeasurables(dtsId)!=null){
//           dailyTimeShareMeasurable = dailyTimeShareMeasurblesRepository.getDailyTimeShareMeasurables(dtsId);
//           dailyTimeShareMeasurables.add(dailyTimeShareMeasurable);
//        }
//        return dailyTimeShareMeasurables;
//    }

    /*need to check whether getting list of dtsMeasurables or measurables since in the TTSDailyShareFragment Measurable model is different from than the db measurable model*/
    @Override
    public List<Measurables> getDailyTimeShareMeasurablesList(Long dtsId) {
        List<Measurables>  dailyTimeShareMeasurables = measurablesRepository.findMeasurablesById(dtsId);
//        MeasurablesIdAndName dailyTimeShareMeasurable;
//        while(measurablesRepository.getMeasurables(dtsId)!=null){
//            dailyTimeShareMeasurable = measurablesRepository.getMeasurables(dtsId);
//            dailyTimeShareMeasurables.add(dailyTimeShareMeasurable);
//        }
        return dailyTimeShareMeasurables;
    }
}
