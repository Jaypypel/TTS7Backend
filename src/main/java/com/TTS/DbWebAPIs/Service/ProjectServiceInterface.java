package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ProjectServiceInterface {

    Project getProjectViaProjectCode(String projectCode);
    String getProjectCodeViaProjectName(String projectName);
    List<String> getProjectCodeList();
    List<String> getProjectNameList();
    Project addProject(Long userId, Long activityID, String projectCode, String projectName, LocalTime createdOn);
    Integer getProjectCount(String username, LocalDate startDate, LocalDate endDate);
    Integer getProjectFrequency(Long userId, LocalDateTime startDate, LocalDateTime endDate);
}