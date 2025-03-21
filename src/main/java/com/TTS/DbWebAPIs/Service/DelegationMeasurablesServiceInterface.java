package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.DTO.AssociatedMeasurableDto;
import com.TTS.DbWebAPIs.DTO.TaskManagementDTO;
import com.TTS.DbWebAPIs.Entity.*;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;

import java.sql.SQLException;
import java.util.List;

public interface DelegationMeasurablesServiceInterface {
    List<Measurables> getAllocatedMeasurableList(Long taskId) throws DatabaseException;


    DelegationMeasurables addDelegationMeasurables(TaskManagement taskManagement,
                                                   Measurables mesrblId,
                                                   Long mesrbQuantity,
                                                   String mesrbUnit) throws DatabaseException;


    void addAllDelegationMeasurables(TaskManagement taskManagement, List<AssociatedMeasurableDto> associatedMeasurableDtos);


}
