package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.TimeShareMeasurables;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;

import java.sql.SQLException;

public interface TimeShareMeasurablesServiceInterface {

    TimeShareMeasurables addTimeShareMeasurables(Long timeShareId, Measurables measuableId, Long measurableQuantity,
                                                 String measurableUnit) throws NotFoundException, DatabaseException;

}
