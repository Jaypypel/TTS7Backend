package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Entity.Project;
import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.ProjectCode;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.ProjectName;
import com.TTS.DbWebAPIs.Repository.ProjectRepository;
import com.TTS.DbWebAPIs.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProjectService implements ProjectServiceInterface  {


    private final ProjectRepository projectRepository;

    private final UserRepository userRepository;

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


    @Override
    public Project addProject(Long userId, Activity activityID, Long projectCode, String projectName, LocalTime createdOn) {
        Project newProject = new Project();
        User inputUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not exit"));
        Set<User> userAssociatedProject = new HashSet<>();
        userAssociatedProject.add(inputUser);
        newProject.setUsersAssociated(userAssociatedProject);
        newProject.setId(projectCode);
        newProject.setName(projectName);
        newProject.setCreatedOn(createdOn);
        return projectRepository.save(newProject);
    }



    @Override
    public Integer getProjectCount(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        return projectRepository.findByUserIdAndDateRange(userId,startDate,endDate);
    }

    @Override
    public Integer getProjectFrequency(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        return projectRepository.findByIdAndDateRange(userId,startDate,endDate);
    }
}
