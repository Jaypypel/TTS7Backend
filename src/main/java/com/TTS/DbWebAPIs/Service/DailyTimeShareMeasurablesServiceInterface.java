package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.TimeShare;

public interface DailyTimeShareMeasurablesServiceInterface {
    DailyTimeShareMeasurables addDailyTimeShareMeasurables(TimeShare timeShareId, Measurables mesrblId, Long mesrbQuantity, String mesrbUnit);
}
