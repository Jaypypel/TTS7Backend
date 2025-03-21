package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.TaskManagement;
import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface    TimeShareRepository  extends JpaRepository<TimeShare, Long> {

    @Query("SELECT t FROM TimeShare t JOIN TaskManagement tm ON tm.id = t.fkTaskManagementId.id " +
            "WHERE tm.taskReceivedUserID.username =:username AND t.dateOfTimeShare BETWEEN :startDate AND :endDate")
    List<TimeShare> findTimeSharesByUserIdAndDateRange(String username, LocalDateTime startDate, LocalDateTime endDate);


    @Query("SELECT ts from TimeShare ts RIGHT JOIN ts.fkTaskManagementId tm WHERE tm.id = :taskId")
    List<TimeShare> findTimeShareByTaskManagementId(Long taskId);



    List<TimeShare> findByFkTaskManagementId(TaskManagement taskManagement);


    @Query("SELECT MAX(ts.id) FROM TimeShare ts")
    Long findMaxTimeShareId();

}
