package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Entity.TimeShareMeasurables;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Repository.TimeShareMeasurablesRepository;
import com.TTS.DbWebAPIs.Repository.TimeShareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class TimeShareMeasurablesService implements TimeShareMeasurablesServiceInterface {


    private  final TimeShareRepository  timeShareRepository;
    private final TimeShareMeasurablesRepository timeShareMeasurablesRepository;

    @Override
    public TimeShareMeasurables addTimeShareMeasurables(Long timeShareId, Measurables measuableId, Long measurableQuantity, String measurableUnit) throws NotFoundException, DatabaseException {
        TimeShare timeShare = timeShareRepository.findById(timeShareId).orElseThrow(() -> new NotFoundException("timeshare not found"));
        TimeShareMeasurables timeShareMeasurables = new TimeShareMeasurables();
        timeShareMeasurables.setFkTimeShareId(timeShare);
        timeShareMeasurables.setFkMeasurablesID(measuableId);
        timeShareMeasurables.setMeasurableQuantity(measurableQuantity);
        timeShareMeasurables.setMeasurableUnit(measurableUnit);
        return timeShareMeasurablesRepository.save(timeShareMeasurables);
    }
}
