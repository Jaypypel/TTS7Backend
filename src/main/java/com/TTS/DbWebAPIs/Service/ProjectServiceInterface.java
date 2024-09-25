package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Repository.InterfaceProjections.ProjectCode;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.ProjectName;

import java.util.List;

public interface ProjectServiceInterface {

    Integer getProjectCode(String projectName);
    List<ProjectCode> getProjectCodeList();
    List<ProjectName> getProjectNameList();
}
