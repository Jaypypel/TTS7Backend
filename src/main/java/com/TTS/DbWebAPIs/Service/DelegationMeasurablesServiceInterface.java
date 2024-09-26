package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.DelegationMeasurables;

import java.util.List;

public interface DelegationMeasurablesServiceInterface {
    List<DelegationMeasurables> getAllocatedMeasurableList(Long taskId);

}
