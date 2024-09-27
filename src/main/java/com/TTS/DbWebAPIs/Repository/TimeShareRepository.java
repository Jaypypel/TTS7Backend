package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface    TimeShareRepository  extends JpaRepository<TimeShare, Long> {

    @Query("SELECT t FROM TimeShare t JOIN TaskManagement tm ON tm.id = t.FKTaskManagementId.id " +
            "WHERE tm.taskReceivedUserID.id = :userId AND t.dateOfTimeShare BETWEEN :startDate AND :endDate")
    List<TimeShare> findTimeSharesByUserIdAndDateRange(User userId, LocalDate startDate, LocalDate endDate);


    @Query("SELECT ts from TimeShare ts RIGHT JOIN ts.FKTaskManagementId tm WHERE tm.id = :taskId")
    List<TimeShare> findTimeShareByTaskManagementId(Long taskId);

    @Query("SELECT MAX(ts.id) FROM TimeShare ts")
    Long findMaxTimeShareId();

}
