package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import com.TTS.DbWebAPIs.Entity.Measurables;
import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MeasurablesRepository extends JpaRepository<Measurables,Long> {


    @Query("SELECT m.id, m.name FROM Measurables m WHERE id IN (SELECT dtsm.fkMeasurablesID FROM Daily_Time_Share_Measurables dtsm WHERE dtsm.fkTimeShareId = :dtsId )")
    Measurables getMeasurables(Long dtsId);
}
