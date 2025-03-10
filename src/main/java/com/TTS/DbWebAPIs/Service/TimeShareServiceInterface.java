package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.DTO.TimeShareDTO;
import com.TTS.DbWebAPIs.Entity.DelegationMeasurables;
import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Entity.TimeShareMeasurables;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Exceptions.UserNotFoundException;


import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface TimeShareServiceInterface {
    List<TimeShare> getTimeShareList(String username, LocalDateTime startDate, LocalDateTime endDate) throws  DatabaseException, UserNotFoundException;
    List<TimeShareDTO> getTimeShareLists(Long taskId) throws NotFoundException, DatabaseException;

    Long getMaxTimeShareId() throws DatabaseException;

//    TimeShare addTimeShare(Long taskId, LocalDateTime date, LocalTime startTime, LocalTime endTime, String timeDifference, String description, LocalTime createdOn, List<TimeShareMeasurables> timeShareMeasurablesList);
    TimeShare addTimeShare(TimeShare timeShare) throws DatabaseException;
//
}
