package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DailyTimeShareRepository extends JpaRepository<DailyTimeShare,Long> {
    List<DailyTimeShare> findAllByUsernameAndDateOfTimeShare(Long userid, LocalDate dateOfTimeShare);
 }
