package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface    TimeShareRepository  extends JpaRepository<TimeShare, Long> {

    @Query("SELECT t FROM TimeShare t JOIN TaskManagement tm ON tm.id = t.taskManagement.id " +
            "WHERE tm.authReceivedUserId = :userId AND t.timeShareDate BETWEEN :startDate AND :endDate")
    List<TimeShare> findTimeSharesByUserIdAndDateRange(User userId, LocalDate startDate, LocalDate endDate);
}
