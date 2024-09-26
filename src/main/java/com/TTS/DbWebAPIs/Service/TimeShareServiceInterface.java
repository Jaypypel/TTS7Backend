package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.TimeShare;

import java.time.LocalDate;
import java.util.List;

public interface TimeShareServiceInterface {
    List<TimeShare> getTimeShareList(Long userId, LocalDate startDate, LocalDate endDate);
}
