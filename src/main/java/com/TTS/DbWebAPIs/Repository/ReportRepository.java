package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.DTO.ReportDTO;
import com.TTS.DbWebAPIs.Entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report,Long> {
    @Query("SELECT new com.TTS.DbWebAPIs.Entity.Report(" +
            "dts.id," +
            "dts.dateOfTimeShare," +
            "dts.projectCode, " +
            "dts.projectName, " +
            "dts.activityName, " +
            "dts.taskName, " +
            "dts.startTime, " +
            "dts.endTime, " +
            "dts.timeDifference, " +
            "m.name, " +
            "dtm.measurableQuantity, " +
            "dtm.measurableUnit, dts.description) " +
            "FROM DailyTimeShare dts " +
            "LEFT JOIN DailyTimeShareMeasurables dtm ON dts.id = dtm.dailyTimeShare.id " +
            "LEFT JOIN Measurables m ON dtm.fkMeasurablesID.id = m.id " +
            "WHERE dts.user.username = :username " +
            "AND dts.dateOfTimeShare BETWEEN :startDate AND :endDate")
    List<Report> findReportsByUserAndDateRange(
            @Param("username") String username,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );

}
