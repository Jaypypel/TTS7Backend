package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.*;
import com.TTS.DbWebAPIs.Repository.*;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor

public class TimeShareService implements TimeShareServiceInterface{

    private final UserRepository userRepository;
    private  final TimeShareRepository timeShareRepository;
    private final TaskManagementRepository taskManagementRepository;
    private final TimeShareMeasurablesRepository timeShareMeasurablesRepository;

    @Override
    public List<TimeShare> getTimeShareList(String username, LocalDateTime startDate, LocalDateTime endDate) {
        User user   = userRepository.findByUsername(username);
        if(user == null || user.getUsername().isEmpty() || user.getUsername().isBlank()) throw new RuntimeException("user not found");
      return timeShareRepository.findTimeSharesByUserIdAndDateRange(username,startDate,endDate );
    }

    @Override
    public List<TimeShare> getTimeShareLists(Long taskId) {
        TaskManagement taskManagement = taskManagementRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found "));
        return timeShareRepository.findTimeShareByTaskManagementId(taskId);

    }

    @Override
    public Long getMaxTimeShareId() {
        return (timeShareRepository.findMaxTimeShareId() != null) ? (timeShareRepository.findMaxTimeShareId() + 1) : 0;
    }

    @Override
    public TimeShare addTimeShare(TimeShare timeShare) {
       // TaskManagement taskManagement = taskManagementRepository.findById(timeShare.getFkTaskManagementId().getId()).orElseThrow(()->new RuntimeException("task not found"));
        return timeShareRepository.save(timeShare);
    }
}
