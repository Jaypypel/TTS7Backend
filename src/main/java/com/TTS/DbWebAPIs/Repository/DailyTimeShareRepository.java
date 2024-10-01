package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import com.TTS.DbWebAPIs.Entity.TimeShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DailyTimeShareRepository extends JpaRepository<DailyTimeShare,Long> {
    List<DailyTimeShare> findAllByIdAndDateOfTimeShare(Long userid, LocalDate dateOfTimeShare);

//    @Query("SELECT dts, m.name, dtsm.measurableQuantity, dtsm.measurableUnit " +
//            "FROM DailyTimeShare dts " +
//            "LEFT JOIN dts.dailyTimeShareMeasurables dtsm " +
//            "LEFT JOIN dtsm.measurables m " +
//            "WHERE dts.user.id = :userId " +
//            "AND dts.dateOfTimeShare BETWEEN :startDate AND :endDate")
     @Query(value = "\"SELECT DAILY_TIME_SHARE.*,MEASURABLES.NAME,DAILY_TIME_SHARE_MEASURABLE.MEASURABLE_QUANTITY,DAILY_TIME_SHARE_MEASURABLE.MEASURABLE_UNIT FROM DAILY_TIME_SHARE\\n\" +\n" +
             "                    \"LEFT JOIN DAILY_TIME_SHARE_MEASURABLE ON DAILY_TIME_SHARE.ID = DAILY_TIME_SHARE_MEASURABLE.FK_TIME_SHARE_ID\\n\" +\n" +
             "                    \"LEFT JOIN MEASURABLES ON DAILY_TIME_SHARE_MEASURABLE.FK_MEASURABLE_ID = MEASURABLES.ID\\n\" +\n" +
             "                    \"WHERE DAILY_TIME_SHARE.FK_AUTHENTICATION_USER_ID = ? AND \\n\" +\n" +
             "                    \"DAILY_TIME_SHARE.DATE_OF_TIME_SHARE BETWEEN ? AND ? \"", nativeQuery = true)
    List<DailyTimeShare> getUserDTSReportDetails(Long userId,LocalDate startDate, LocalDate endDate);

    @Query("SELECT dts.timeDifference " +
            "FROM DailyTimeShare dts " +
            "WHERE dts.user.id = :userId " +
            "AND dts.dateOfTimeShare BETWEEN :startDate AND :endDate")
    List<String> findTimeDiffByIdAndDateRange(Long userId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT MAX(dts.id)  FROM DailyTimeShare dts")
    Integer findMaxDailyTimeShareId();

 }
