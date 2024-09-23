package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Repository.DailyTimeShareMeasurblesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DailyTimeShareMeasurablesService implements DailyTimeShareMeasurablesServiceInterface {


    DailyTimeShareMeasurblesRepository dailyTimeShareMeasurblesRepository;

    @Override
    public DailyTimeShareMeasurables addDailyTimeShareMeasurables(TimeShare timeShareId, Measurables mesrblId, Long mesrbQuantity, String mesrbUnit) {
        DailyTimeShareMeasurables dailyTimeShareMeasurables = new DailyTimeShareMeasurables();
        dailyTimeShareMeasurables.setFkTimeShareId(timeShareId);
        dailyTimeShareMeasurables.setFkMeasurablesID(mesrblId);
        dailyTimeShareMeasurables.setMeasurableQuantity(mesrbQuantity);
        dailyTimeShareMeasurables.setMeasurableUnit(mesrbUnit);
        return dailyTimeShareMeasurblesRepository.save(dailyTimeShareMeasurables);
    }
}
