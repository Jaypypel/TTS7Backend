package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.MeasurablesIdAndName;

import java.util.List;
import java.util.Optional;

public interface DailyTimeShareMeasurablesServiceInterface {

    DailyTimeShareMeasurables addDailyTimeShareMeasurables(TimeShare timeShareId, Measurables mesrblId, Long mesrbQuantity, String mesrbUnit);

    List<Measurables> getDailyTimeShareMeasurablesList(Long dtsId);

}
