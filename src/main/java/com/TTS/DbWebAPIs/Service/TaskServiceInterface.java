package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Task;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.TaskName;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TaskServiceInterface {
    List<String> getTaskNameList(String userId) throws SQLException;

    Integer getTaskCount(String username, LocalDate startDate, LocalDate endDate) throws SQLException;

    Integer getTaskFreqeuncyCount(String username, LocalDate startDate, LocalDate endDate)  throws SQLException;

    Task addTask(String username, Long activtyId, String taskName, String createdOn)  throws SQLException;

    List<String> getTaskNames()  throws SQLException;
}
