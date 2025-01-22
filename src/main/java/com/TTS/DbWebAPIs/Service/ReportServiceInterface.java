package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Report;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ReportServiceInterface {
    List<Report> getUserDTSReport(String username, LocalDate startDate, LocalDate endDate) throws SQLException;
}
