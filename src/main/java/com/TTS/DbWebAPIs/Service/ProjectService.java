package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Entity.Project;
import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Exceptions.UserNotFoundException;
import com.TTS.DbWebAPIs.Repository.ActivityRepository;
import com.TTS.DbWebAPIs.Repository.ProjectRepository;
import com.TTS.DbWebAPIs.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
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
    public Project getProjectViaProjectCode(String projectCode) throws DatabaseException {
        return projectRepository.findByProjectCode(projectCode);
    }

    @Override
    public String getProjectCodeViaProjectName(String projectName)  throws DatabaseException, NotFoundException {
        String projectCode =  projectRepository.findByProjectName(projectName);
        if(projectCode == null) throw new NotFoundException("project code not found by the name");
        return projectCode;

    }

    @Override
    public List<String> getProjectCodeList()throws DatabaseException {
        return projectRepository.findByProjectCodeList();
    }

    @Override
    public List<String> getProjectNameList()throws DatabaseException {
        return projectRepository.findByName();
    }

    @Transactional
    @Override
    public Project addProject(String userId, Long activityID, String projectCode, String projectName, String createdOn) throws DatabaseException, UserNotFoundException,NotFoundException {
        Project newProject = new Project();
        User user =  userRepository
                .findByUsername(userId)
                .orElseThrow(() -> new UserNotFoundException("Username not found"));
        Activity activity = activityRepository
                .findById(activityID)
                .orElseThrow(()-> new NotFoundException("activity not found"));
        newProject.setUser(user);
        newProject.setActivitiesAssociated(activity);
        newProject.setProjectCode(projectCode);
        newProject.setName(projectName);
        newProject.setCreatedOn(createdOn);
        return projectRepository.save(newProject);
    }



    @Override
    public Integer getProjectCount(String username, LocalDate startDate, LocalDate endDate) throws DatabaseException {
        return projectRepository.findByUserIdAndDateRange(username,startDate,endDate);
    }

    @Override
    public Integer getProjectFrequency(Long userId, LocalDateTime startDate, LocalDateTime endDate) throws DatabaseException {
        return projectRepository.findByIdAndDateRange(userId,startDate,endDate);
    }
}