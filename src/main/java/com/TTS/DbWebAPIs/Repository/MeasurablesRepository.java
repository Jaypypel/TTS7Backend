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
            "IN (SELECT dtsm.fkMeasurablesID FROM DailyTimeShareMeasurables dtsm WHERE dtsm.fkTimeShareId = :dtsId )")
    Measurables getMeasurables(Long dtsId);

//    @Query("SELECT COUNT(DISTINCT dtm.measurableId) "  +
//            " FROM DailyTimeShare dts "  +
//            " LEFT JOIN dts.dailyTimeShareMeasurables dtm " +
//            " WHERE dts.user.id = :userId AND "  +
//            " dts.dateOfTimeShare BETWEEN :startDate AND :endDate")
     @Query(value = "SELECT COUNT(DISTINCT dtm.measurable_id) " +
        "FROM DAILY_TIME_SHARE dts " +
        "LEFT JOIN DAILY_TIME_SHARE_MEASURABLE dtm ON dts.id = dtm.fk_time_share_id " +
        "WHERE dts.fk_user_id = :userId " +
        "AND dts.date_of_time_share BETWEEN :startDate AND :endDate",
        nativeQuery = true)
    Integer findByByUserAndDateRange(  Long userId, LocalDate startDate, LocalDate endDate);
    //need to check
    List<Measurables> findbyUserId(Long userId);

    @Query("SELECT m.id,m.Name FROM Measurables m WHERE m.id IN " +
            "(SELECT dm.fkMeasurableId.id FROM DelegationMeasurable dm WHERE dm.fkTaskManagementID.id = :taskId)")
    List<Measurables> findAllocatedMeasurablesByTaskId( Long taskId);
}
