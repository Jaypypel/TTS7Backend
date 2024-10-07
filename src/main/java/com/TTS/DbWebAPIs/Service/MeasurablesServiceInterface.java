package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.MeasurablesIdAndName;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MeasurablesServiceInterface {
     List<Measurables> getDTSMeasurablesList(Long dtsId);

     List<Measurables> getMeasurableList();

  //   List<Measurables> getAllocatedMeasurableList(Long taskId);

     Integer getMeasurableCount(String username, LocalDate startDate, LocalDate endDate);
     //need to return names of measurables rather object
     List<Measurables> getMeasurableListForUsername(String username);

     Measurables addMeasurable(String username, String measurableName, LocalTime createdOn);
}
