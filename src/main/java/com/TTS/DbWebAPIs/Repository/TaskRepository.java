package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.Task;

import com.TTS.DbWebAPIs.Repository.InterfaceProjections.TaskName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("SELECT DISTINCT(t.name) as Name FROM Task t WHERE t.user.username =:userId")
    List<String> findById(String userId);

//    @Query("SELECT COUNT(DISTINCT(d.taskName)) FROM DailyTimeShare d WHERE d.user.username =:username" +
//            " AND d.dateOfTimeShare BETWEEN :startDate AND :endDate")
    @Query(value = "SELECT COUNT(DISTINCT(dts.task_name)) FROM daily_time_share dts WHERE LOWER(dts.userid) = LOWER(:username) AND dts.date_of_time_share BETWEEN :startDate AND :endDate", nativeQuery = true)
    Integer findByUserIdAndStartDateAndEndDate(String username, LocalDate startDate, LocalDate endDate);

    @Query(value = "SELECT COUNT(dts.task_name) FROM daily_time_share dts WHERE LOWER(dts.userid) = LOWER(:username) AND dts.date_of_time_share BETWEEN :startDate AND :endDate", nativeQuery = true)
    Integer findByIdAndStartDateAndEndDate(String username, LocalDate startDate, LocalDate endDate);

    @Query("SELECT DISTINCT t.name FROM Task t")
    List<String> findAllDistinctName();
}
