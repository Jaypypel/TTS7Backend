package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.Task;

import com.TTS.DbWebAPIs.Repository.InterfaceProjections.TaskName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("SELECT DISTINCT(t.name) as name FROM Task WHERE t.user.id =: userID")
    List<TaskName> findByUser(String userId);

    @Query("SELECT COUNT(DISTINCT(dts.taskName)) FROM DailyTimeShare dts dts.user.id =: userId " +
            "AND dts.dateOfTimeShare BETWEEN dts.startDate =: startDate AND dts.endDate =: endDate")
    Integer findByUserIdAndStartDateAndEndDate(Long userId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT COUNT(dts.taskName) FROM DailyTimeShare dts dts.user.id =: userId " +
            "AND dts.dateOfTimeShare BETWEEN dts.startDate =: startDate AND dts.endDate =: endDate")
    Integer findByUserIdAndStartDateAndEndDateToGetFrequency(Long userId, LocalDate startDate, LocalDate endDate);
}
