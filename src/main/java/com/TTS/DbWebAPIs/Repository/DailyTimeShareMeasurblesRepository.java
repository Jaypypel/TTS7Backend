package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyTimeShareMeasurblesRepository extends JpaRepository<DailyTimeShareMeasurables, Long> {
    //DailyTimeShareMeasurables addDailyTimeShareMeasurables(Long dlyTmeShrMesrblId, Long tmShrId, Long msrblId, String msrblQuanty, String  msrblUnit);
}
