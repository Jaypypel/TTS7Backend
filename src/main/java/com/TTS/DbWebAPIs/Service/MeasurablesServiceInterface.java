package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Measurables;

import java.util.List;

public interface MeasurablesServiceInterface {
     List<Measurables> getMeasurablesList(Long dtsId);
}
