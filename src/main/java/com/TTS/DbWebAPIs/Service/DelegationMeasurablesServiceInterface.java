package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.*;

import java.sql.SQLException;
import java.util.List;

public interface DelegationMeasurablesServiceInterface {
    List<Measurables> getAllocatedMeasurableList(Long taskId) throws SQLException;


    DelegationMeasurables addDelegationMeasurables(TaskManagement taskManagement,
                                                   Measurables mesrblId,
                                                   Long mesrbQuantity,
                                                   String mesrbUnit) throws SQLException;


}
