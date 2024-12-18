package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Report;

import java.util.List;

public interface ReportServiceInterface {
    List<Report> getUserDTSReport(String username, String startDate, String endDate);
}
