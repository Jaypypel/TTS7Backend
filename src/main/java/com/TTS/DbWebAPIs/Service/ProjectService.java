package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Entity.Project;
import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Repository.ActivityRepository;
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

    private  final ActivityRepository activityRepository;


    @Override
    public Project getProjectViaProjectCode(String projectCode) {
        return projectRepository.findByProjectCode(projectCode);
    }

    @Override
    public String getProjectCodeViaProjectName(String projectName) {
        String projectCode =  projectRepository.findByProjectName(projectName);
        if(projectCode == null) throw new NotFoundException("project code not found by the name");
        return projectCode;

    }

    @Override
    public List<String> getProjectCodeList() {
        return projectRepository.findByProjectCodeList();
    }

    @Override
    public List<String> getProjectNameList() {
        return projectRepository.findByName();
    }


    @Override
    public Project addProject(String userId, Long activityID, String projectCode, String projectName, String createdOn) {
        Project newProject = new Project();
        User inputUser = userRepository.findByUsername(userId) != null ? userRepository.findByUsername(userId): null;
        if (inputUser == null) throw new NotFoundException("Username not found");

        Set<User> userAssociatedProject = new HashSet<>();
        userAssociatedProject.add(inputUser);
        newProject.setUsersAssociated(userAssociatedProject);
        Activity activity = activityRepository.findById(activityID).orElseThrow(()-> new RuntimeException("activity not exist"));
        newProject.setActivitiesAssociated(activity);
        newProject.setProjectCode(projectCode);
        newProject.setName(projectName);
        newProject.setCreatedOn(createdOn);
        return projectRepository.save(newProject);
    }



    @Override
    public Integer getProjectCount(String username, LocalDate startDate, LocalDate endDate) {
        return projectRepository.findByUserIdAndDateRange(username,startDate,endDate);
    }

    @Override
    public Integer getProjectFrequency(Long userId, LocalDateTime startDate, LocalDateTime endDate) {
        return projectRepository.findByIdAndDateRange(userId,startDate,endDate);
    }
}