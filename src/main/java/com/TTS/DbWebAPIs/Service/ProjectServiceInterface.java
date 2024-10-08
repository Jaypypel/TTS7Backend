package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Entity.Project;
import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.ProjectCode;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.ProjectName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface ProjectServiceInterface {

    Project getProjectCode(String projectName);
    List<String> getProjectCodeList();
    List<String> getProjectNameList();
    Project addProject(Long userId, Long activityID, String projectCode, String projectName, LocalTime createdOn);
    Integer getProjectCount(Long userId, LocalDateTime startDate, LocalDateTime endDate);
    Integer getProjectFrequency(Long userId, LocalDateTime startDate, LocalDateTime endDate);
}