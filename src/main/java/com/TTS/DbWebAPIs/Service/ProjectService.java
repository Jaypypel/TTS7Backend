package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Repository.InterfaceProjections.ProjectCode;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.ProjectName;
import com.TTS.DbWebAPIs.Repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService implements ProjectServiceInterface  {


    private final ProjectRepository projectRepository;

    @Override
    public Integer getProjectCode(String projectName) {
        return projectRepository.findByProjectCode(projectName);
    }

    @Override
    public List<ProjectCode> getProjectCodeList() {
        return projectRepository.findById();
    }

    @Override
    public List<ProjectName> getProjectNameList() {
        return projectRepository.findByName();
    }
}
