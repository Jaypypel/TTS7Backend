package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.MeasurableUnit;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Repository.MeasurableUnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MeasurableUnitService implements MeasurableUnitServiceInterface {

    private final MeasurableUnitRepository measurableUnitRepository;

    @Override
    public List<MeasurableUnit> getMeasurableUnitList() throws DatabaseException {
        return measurableUnitRepository.findAll();
    }
}
