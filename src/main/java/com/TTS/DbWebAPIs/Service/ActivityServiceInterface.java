package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.DTO.ActivityDto;
import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ActivityServiceInterface {

   // Page<String> getActivityNames(Pageable pageable);
    List<String> getActivityNames() throws DatabaseException;
    //need to return only id and name as an object
    List<ActivityDto> getActivityList(String userId) throws UserNotFoundException, DatabaseException;

    Activity addActivity(String username, String activityName, String createdOn) throws UserNotFoundException
            ,DatabaseException;

    Integer getActivityCount(String username, LocalDate startDate, LocalDate endDate) throws DatabaseException;

    Activity getActivity(String name) throws DatabaseException;

    List<String> getActivityNamesByUsername(String name) throws UserNotFoundException, DatabaseException;
}
