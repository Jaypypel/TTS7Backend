package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ActivityServiceInterface {

   // Page<String> getActivityNames(Pageable pageable);
    List<String> getActivityNames() throws SQLException;
    //need to return only id and name as an object
    List<Activity> getActivityList(String userId) throws SQLException;

    Activity addActivity(String username, String activityName, String createdOn) throws SQLException;

    Integer getActivityCount(String username, LocalDate startDate, LocalDate endDate) throws SQLException;

    Activity getActivity(String name) throws SQLException;

    List<String> getActivityNamesByUsername(String name) throws SQLException;
}
