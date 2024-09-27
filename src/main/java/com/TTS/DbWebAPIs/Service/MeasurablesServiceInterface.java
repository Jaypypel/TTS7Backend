package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Measurables;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MeasurablesServiceInterface {
     List<Measurables> getDTSMeasurablesList(Long dtsId);

     List<Measurables> getMeasurableList();

  //   List<Measurables> getAllocatedMeasurableList(Long taskId);

     Integer getMeasurableCount(Long userId, LocalDate startDate, LocalDate endDate);
     //need to return names of measurables rather object
     List<Measurables> getMeasurableListForUserID(Long userId);

     Measurables addMeasurable(Long userId, String measurableName, LocalTime createdOn);
}
