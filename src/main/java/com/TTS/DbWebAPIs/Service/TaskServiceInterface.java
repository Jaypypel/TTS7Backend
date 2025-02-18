package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Task;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Exceptions.UserNotFoundException;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.TaskName;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TaskServiceInterface {
    List<String> getTaskNameList(String userId) throws DatabaseException;

    Integer getTaskCount(String username, LocalDate startDate, LocalDate endDate) throws DatabaseException;

    Integer getTaskFreqeuncyCount(String username, LocalDate startDate, LocalDate endDate)  throws DatabaseException;

    Task addTask(String username, Long activtyId, String taskName, String createdOn)  throws  DatabaseException, NotFoundException, UserNotFoundException;

    List<String> getTaskNames()  throws DatabaseException;
}
