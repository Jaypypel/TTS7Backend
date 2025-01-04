package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Project;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ProjectServiceInterface {

    Project getProjectViaProjectCode(String projectCode) throws SQLException;
    String getProjectCodeViaProjectName(String projectName) throws SQLException;
    List<String> getProjectCodeList() throws SQLException;
    List<String> getProjectNameList() throws SQLException;
    Project addProject(String userId, Long activityID, String projectCode, String projectName, String createdOn)
            throws SQLException;
    Integer getProjectCount(String username, LocalDate startDate, LocalDate endDate) throws SQLException;
    Integer getProjectFrequency(Long userId, LocalDateTime startDate, LocalDateTime endDate) throws SQLException;
}