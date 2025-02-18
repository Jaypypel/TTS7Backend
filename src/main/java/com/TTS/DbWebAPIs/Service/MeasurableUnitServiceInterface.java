package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.MeasurableUnit;
import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;

import java.util.List;

public interface MeasurableUnitServiceInterface
{
//    List<Measurables> getMeasurablesList(Long dtsId);
      List<MeasurableUnit> getMeasurableUnitList() throws DatabaseException;

}
