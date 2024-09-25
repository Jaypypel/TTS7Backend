package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Measurables;

import java.util.List;

public interface MeasurableUnitServiceInterface
{
    List<Measurables> getMeasurablesList(Long dtsId);
}
