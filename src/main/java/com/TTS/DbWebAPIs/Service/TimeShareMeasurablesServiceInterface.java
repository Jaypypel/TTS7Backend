package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.TimeShareMeasurables;

public interface TimeShareMeasurablesServiceInterface {

    TimeShareMeasurables addTimeShareMeasurables(Long timeShareId, Measurables measuableId, Long measurableQuantity,
                                                 String measurableUnit);

}
