package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DailyTimeShareRepository extends JpaRepository<DailyTimeShare,Long> {
    List<DailyTimeShare> findAllByUserUsernameAndDateOfTimeShare(String username, LocalDate dateOfTimeShare);

    //    @Query("SELECT dts, m.name, dtsm.measurableQuantity, dtsm.measurableUnit " +
//            "FROM DailyTimeShare dts " +
//            "LEFT JOIN dts.dailyTimeShareMeasurables dtsm " +
//            "LEFT JOIN dtsm.measurables m " +
//            "WHERE dts.user.id = :userId " +
//            "AND dts.dateOfTimeShare BETWEEN :startDate AND :endDate")
//     @Query(value = "\"SELECT DAILY_TIME_SHARE.*,MEASURABLES.NAME,DAILY_TIME_SHARE_MEASURABLE.MEASURABLE_QUANTITY,DAILY_TIME_SHARE_MEASURABLE.MEASURABLE_UNIT FROM DAILY_TIME_SHARE\\n\" +\n" +
//             "                    \"LEFT JOIN DAILY_TIME_SHARE_MEASURABLE ON DAILY_TIME_SHARE.ID = DAILY_TIME_SHARE_MEASURABLE.FK_TIME_SHARE_ID\\n\" +\n" +
//             "                    \"LEFT JOIN MEASURABLES ON DAILY_TIME_SHARE_MEASURABLE.FK_MEASURABLE_ID = MEASURABLES.ID\\n\" +\n" +
//             "                    \"WHERE DAILY_TIME_SHARE.FK_AUTHENTICATION_USER_ID = ? AND \\n\" +\n" +
//             "                    \"DAILY_TIME_SHARE.DATE_OF_TIME_SHARE BETWEEN ? AND ? \"", nativeQuery = true)
    @Query(value = """
    SELECT daily_time_share.*,
           measurables.name, 
           daily_time_share_measurables.measurable_quantity, 
           daily_time_share_measurables.measurable_unit 
    FROM daily_time_share
    LEFT JOIN daily_time_share_measurables 
           ON daily_time_share.id = daily_time_share_measurables.daily_timeshare_id
    LEFT JOIN measurables 
           ON daily_time_share_measurables.measurables_id = measurables.id
    WHERE daily_time_share.userid = ?1 
      AND daily_time_share.date_of_time_share BETWEEN ?2 AND ?3
    """, nativeQuery = true)

    List<DailyTimeShare> getUserDTSReportDetails(String username, LocalDate startDate, LocalDate endDate);

//    @Query("SELECT dts.timeDifference " +
//            "FROM DailyTimeShare dts " +
//            "WHERE dts.user.username = :username " +
//            "AND dts.dateOfTimeShare BETWEEN :startDate AND :endDate")

    @Query(value="SELECT daily_time_share.time_difference FROM daily_time_share WHERE daily_time_share.userid = ?1 AND daily_time_share.date_of_time_share BETWEEN ?2 AND ?3",nativeQuery = true)
    List<String> findByUserUsernameAndStartDateAndEndDate(String username, LocalDate startDate, LocalDate endDate);

    //@Query("SELECT MAX(dts.id)  FROM DailyTimeShare dts")

    @Query(value = "SELECT MAX(id) FROM daily_time_share", nativeQuery = true)
    Long findMaxDailyTimeShareId();

 }
