package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Project;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Exceptions.UserNotFoundException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ProjectServiceInterface {

    Project getProjectViaProjectCode(String projectCode) throws DatabaseException;
    String getProjectCodeViaProjectName(String projectName) throws DatabaseException, NotFoundException;
    List<String> getProjectCodeList() throws DatabaseException;
    List<String> getProjectNameList() throws DatabaseException;
    Project addProject(String userId, Long activityID, String projectCode, String projectName, String createdOn)
            throws DatabaseException, UserNotFoundException,NotFoundException;
    Integer getProjectCount(String username, LocalDate startDate, LocalDate endDate) throws DatabaseException;
    Integer getProjectFrequency(Long userId, LocalDateTime startDate, LocalDateTime endDate) throws DatabaseException;
}