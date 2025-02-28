package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.DTO.ActivityDto;
import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Exceptions.UserAlreadyExistsException;
import com.TTS.DbWebAPIs.Exceptions.UserNotFoundException;
import com.TTS.DbWebAPIs.Repository.ActivityRepository;
import com.TTS.DbWebAPIs.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService implements ActivityServiceInterface{

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;


    //get a list of names of activity
    @Cacheable("activityNames")
    @Override
    public List<String> getActivityNames() throws DatabaseException {
        return activityRepository.getActivityNames();
    }

    //    @Override
//    public Page<String> getActivityNames(Pageable pageable) {
//        return activityRepository.getActivityNames(pageable);
//    }


    @Cacheable("activities")
    //get a list of activity via userId
    @Override
    public List<ActivityDto> getActivityList(String username) throws UserNotFoundException, DatabaseException {
       User existingUser = userRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UserNotFoundException("user not found by username : "+username)
                );
        return activityRepository
                .getActivityList(
                        existingUser
                                .getUsername()
                )
                .stream()
                .map(ActivityDto::mapActivityToActivityDto)
                .collect(
                        Collectors.toList()
                );
    }

    @CacheEvict(value = { "activities", "activityNamesForUser","activityNames"}, allEntries = true)
    //add an activity
    @Override
    public Activity addActivity(String username, String activityName, String createdOn) throws UserNotFoundException
    ,DatabaseException
    {
        Activity inputActivity = new Activity();
        User existingUser = userRepository
                .findByUsername(username)
                        .orElseThrow(() -> new UserNotFoundException("user not found by username : "+username));
        inputActivity.setUser(existingUser);
        inputActivity.setName(activityName);
        inputActivity.setCreatedOn(createdOn);
        return activityRepository .save(inputActivity);
    }

    //getActivityCount
    @Override
    public Integer getActivityCount(String username, LocalDate startDate, LocalDate endDate) throws DatabaseException {
        userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("username not found"));
        return activityRepository.ActivityCount(username,startDate,endDate);
    }

    @Override
    public Activity getActivity(String name) throws DatabaseException {
        return activityRepository.findByName(name);
    }


    @Cacheable("activityNamesForUser")
    @Override
    public List<String> getActivityNamesByUsername(String username) throws UserNotFoundException, DatabaseException {
        userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("user not found by username : "+username));
        return   activityRepository.getActivityNamesbyUserName(username);
    }


}
