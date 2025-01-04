package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Report;
import com.TTS.DbWebAPIs.Repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService implements ReportServiceInterface{

    private  final ReportRepository reportRepository;

    @Override
    public List<Report> getUserDTSReport(String username, String startDate, String endDate) throws SQLException {
        return reportRepository.findReportsByUserAndDateRange(username,startDate,endDate);
    }
}
