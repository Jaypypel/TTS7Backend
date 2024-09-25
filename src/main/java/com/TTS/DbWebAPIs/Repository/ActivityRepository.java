package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.Activity;
import com.TTS.DbWebAPIs.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity,Long> {

    @Query("Select DISTINCT a.name from Activity a")
    List<String> getActivityNames();

    @Query("Select DISTINCT a from Activity where a.user.id =:userId")
    List<Activity> getActivityList(String userId);

    @Query("Select COUNT(DISTINCT(dts.activityName)) From Daily_Time_Share dts WHERE  dts.user.id = :userID AND dts.dateOFTimeShare" +
            " BETWEEN dts.startDate = :startDate AND dts.endDate = :endDate")
    Integer ActivityCount(User userId, String startDate, String endDate);
}
