package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Entity.Project;
import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.ProjectCode;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.ProjectName;

import java.time.LocalTime;
import java.util.List;

public interface ProjectServiceInterface {

    Integer getProjectCode(String projectName);
    List<ProjectCode> getProjectCodeList();
    List<ProjectName> getProjectNameList();
    Project addProject(Long userId, Activity activityID, Long projectCode, String projectName, LocalTime createdOn);
}
