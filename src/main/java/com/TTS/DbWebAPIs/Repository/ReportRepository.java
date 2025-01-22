package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.DTO.ReportDTO;
import com.TTS.DbWebAPIs.Entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report,Long> {

    @Transactional
    @Procedure(name = "getUserReportWithInDateRange")
    List<Report> getUserReportWithInDateRange(
            @Param("p_username") String username,
            @Param("p_startDate") LocalDate startDate,
            @Param("p_endDate") LocalDate endDate
    );
}
