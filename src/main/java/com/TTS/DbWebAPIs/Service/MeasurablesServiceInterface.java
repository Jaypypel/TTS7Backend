package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.UserNotFoundException;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.MeasurablesIdAndName;
import org.springframework.cache.annotation.Cacheable;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MeasurablesServiceInterface {
     List<Measurables> getDTSMeasurablesList(Long dtsId) throws DatabaseException;


     List<Measurables> getMeasurableList() throws DatabaseException;

  //   List<Measurables> getAllocatedMeasurableList(Long taskId);

     Integer getMeasurableCount(String username, LocalDate startDate, LocalDate endDate) throws DatabaseException;
     //need to return names of measurables rather object
     List<String> getMeasurableListForUsername(String username) throws DatabaseException;

     Measurables addMeasurable(String username, String measurableName, String createdOn) throws DatabaseException, UserNotFoundException;
}
