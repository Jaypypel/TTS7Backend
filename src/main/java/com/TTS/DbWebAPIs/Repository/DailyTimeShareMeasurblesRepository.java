package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DailyTimeShareMeasurblesRepository extends JpaRepository<DailyTimeShareMeasurables, Long> {

//    @Query("SELECT m.id, m.name FROM Measurables m WHERE id = ANY(SELECT dtsm.fkMeasurablesID FROM Daily_Time_Share_Measurables dtsm WHERE dtsm.fkTimeShareId = :dtsId )")
//    DailyTimeShareMeasurables getDailyTimeShareMeasurables(Long dtsId);
}
