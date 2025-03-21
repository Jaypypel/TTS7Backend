package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.DTO.DailyTimeShareDTO;
import com.TTS.DbWebAPIs.DTO.AssociatedMeasurableDto;
import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.MeasurablesIdAndName;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DailyTimeShareMeasurablesServiceInterface {

    DailyTimeShareMeasurables addDailyTimeShareMeasurable(Long dailyTimeShareId,
                                                           Measurables mesrblId,
                                                           Long mesrbQuantity,
                                                           String mesrbUnit) throws DatabaseException, NotFoundException;

    List<Measurables> getDailyTimeShareMeasurablesList(Long dtsId)  throws DatabaseException;

    void addDailyTimeShareMeasurables(DailyTimeShare dailyTimeShare, List<AssociatedMeasurableDto> dailyTimeShareMeasurables);

}
