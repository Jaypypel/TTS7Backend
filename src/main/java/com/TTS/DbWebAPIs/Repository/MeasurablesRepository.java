package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import com.TTS.DbWebAPIs.Entity.Measurables;
import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MeasurablesRepository extends JpaRepository<Measurables,Long> {


    @Query("SELECT m.id, m.name FROM Measurables m WHERE id " +
            "IN (SELECT dtsm.fkMeasurablesID FROM Daily_Time_Share_Measurables dtsm WHERE dtsm.fkTimeShareId = :dtsId )")
    Measurables getMeasurables(Long dtsId);

    @Query("SELECT COUNT(DISTINCT dtm.measurableId) "  +
            " FROM DailyTimeShare dts "  +
            " LEFT JOIN dts.dailyTimeShareMeasurables dtm " +
            " WHERE dts.authenticationUser.id = :userId AND "  +
            " dts.dateOfTimeShare BETWEEN :startDate AND :endDate")
    Integer findByByUserAndDateRange(  Long userId, LocalDate startDate, LocalDate endDate);

    List<Measurables> findbyUserId(Long userId);

    @Query("SELECT m.id,  m.Name FROM Measurable m WHERE m.id IN " +
            "(SELECT dm.measurable.id FROM DelegationMeasurable dm WHERE dm.taskManagementId.id = :taskId)")
    List<Measurables> findAllocatedMeasurablesByTaskId( Long taskId);
}
