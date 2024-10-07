package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.DailyTimeShareMeasurables;
import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.MeasurablesIdAndName;
import org.hibernate.boot.archive.internal.JarProtocolArchiveDescriptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MeasurablesRepository extends JpaRepository<Measurables,Long> {


    @Query("SELECT m FROM Measurables m WHERE id " +
            "IN (SELECT dtsm.fkMeasurablesID.id FROM DailyTimeShareMeasurables dtsm WHERE dtsm.fkTimeShareId.id = :dtsId )")
    List<Measurables> findMeasurablesById(Long dtsId);

//    @Query("SELECT COUNT(DISTINCT dtm.measurableId) "  +
//            " FROM DailyTimeShare dts "  +
//            " LEFT JOIN dts.dailyTimeShareMeasurables dtm " +
//            " WHERE dts.user.id = :userId AND "  +
////            " dts.dateOfTimeShare BETWEEN :startDate AND :endDate")
//     @Query(value = "SELECT COUNT(DISTINCT dtm.measurable_id) " +
//        "FROM DailyTimeShare dts " +
//        "LEFT JOIN DailyTimeShareMeasurables dtm ON dts.id = dtm.fk_time_share_id.id " +
//        "WHERE dts.fk_user_id.id = :userId " +
//        "AND dts.date_of_time_share BETWEEN :startDate AND :endDate",
//        nativeQuery = true)
    @Query(value = "SELECT COUNT(DISTINCT measurables_id) " +
        "FROM daily_time_share dts " + // table name for DailyTimeShare
        "LEFT JOIN daily_time_share_measurables dtm ON dts.id = dtm.timeshare_id " + // Use the correct column for foreign key
        "WHERE dts.user.username = :username " + // Directly reference the column for user ID
        "AND dts.date_of_time_share BETWEEN :startDate AND :endDate",
        nativeQuery = true)
    Integer findByUserAndDateRange(String username, LocalDate startDate, LocalDate endDate);
    //need to check
    @Query("SELECT  m FROM Measurables m WHERE m.user.username = :username")
    List<Measurables> findByUsername(String username);

    @Query("SELECT m.id,m.name FROM Measurables m WHERE m.id IN " +
            "(SELECT dm.fkMeasurableId.id FROM DelegationMeasurables dm WHERE dm.fkTaskManagementID.id = :taskId)")
    List<Measurables> findAllocatedMeasurablesByTaskId( Long taskId);
}
