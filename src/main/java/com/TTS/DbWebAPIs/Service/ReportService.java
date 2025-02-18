package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Report;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService implements ReportServiceInterface{

    private  final ReportRepository reportRepository;

    @Override
    public List<Report> getUserDTSReport(String username, LocalDate startDate, LocalDate endDate) throws DatabaseException {
        return reportRepository.getUserReportWithInDateRange(username,startDate,endDate);
    }
}
