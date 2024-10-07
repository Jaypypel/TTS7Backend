package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity,Long> {

    @Query("Select DISTINCT a.name from Activity a")
    List<String> getActivityNames();

    @Query("Select DISTINCT a from Activity a where a.user.username =:username")
    List<Activity> getActivityList(String username);

    @Query("Select COUNT(DISTINCT(dts.activityName)) From DailyTimeShare dts WHERE  dts.user.username = :username AND dts.dateOfTimeShare" +
            " BETWEEN :startDate AND  :endDate")
    Integer ActivityCount(String username, LocalDate startDate, LocalDate endDate);

    Activity findByName(String name);
}
