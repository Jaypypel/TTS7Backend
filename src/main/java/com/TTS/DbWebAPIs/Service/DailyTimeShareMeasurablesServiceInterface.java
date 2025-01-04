package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.MeasurablesIdAndName;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DailyTimeShareMeasurablesServiceInterface {

    DailyTimeShareMeasurables addDailyTimeShareMeasurables(Long dailyTimeShareId,
                                                           Measurables mesrblId,
                                                           Long mesrbQuantity,
                                                           String mesrbUnit) throws SQLException;

    List<Measurables> getDailyTimeShareMeasurablesList(Long dtsId)  throws SQLException;;

}
