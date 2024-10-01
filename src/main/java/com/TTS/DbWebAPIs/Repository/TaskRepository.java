package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.Task;

import com.TTS.DbWebAPIs.Repository.InterfaceProjections.TaskName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("SELECT DISTINCT(t.name) as Name FROM Task t WHERE t.user.id =:userId")
    List<TaskName> findById(String userId);

    @Query("SELECT COUNT(DISTINCT(d.taskName)) FROM DailyTimeShare d WHERE d.user.id =:userId" +
            " AND d.dateOfTimeShare BETWEEN :startDate AND :endDate")
    Integer findByUserIdAndStartDateAndEndDate(Long userId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT COUNT(d.taskName) FROM DailyTimeShare d WHERE d.user.id =:userId" +
            " AND d.dateOfTimeShare BETWEEN :startDate AND :endDate")
    Integer findByIdAndStartDateAndEndDate(Long userId, LocalDate startDate, LocalDate endDate);
}
