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
    public List<TimeShare> getTimeShareList(Long userId, LocalDate startDate, LocalDate endDate) {
     User user   = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("user not found"));
      return timeShareRepository.findTimeSharesByUserIdAndDateRange(user,startDate,endDate );
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
    public TimeShare addTimeShare(Long taskId, LocalDateTime date, LocalTime startTime, LocalTime endTime, String timeDifference, String description, LocalTime createdOn, List<TimeShareMeasurables> timeShareMeasurablesList) {
        TaskManagement taskManagement = taskManagementRepository.findById(taskId).orElseThrow(()->new RuntimeException("task not found"));
        TimeShare timeShare = new TimeShare();
        timeShare.setFkTaskManagementId(taskManagement);
        timeShare.setDateOfTimeShare(date);
        timeShare.setStartTime(startTime);
        timeShare.setEndTime(endTime);
        timeShare.setTimeDifference(timeDifference);
        timeShare.setDescription(description);
        timeShare.setCreatedOn(createdOn);
        TimeShare saveTimeShare = timeShareRepository.save(timeShare);
        TimeShareMeasurablesService timeShareMeasurablesService = new TimeShareMeasurablesService(timeShareRepository);
        timeShareMeasurablesList.forEach(timeShareMeasurables -> {
            timeShareMeasurablesService.addTimeShareMeasurables(saveTimeShare.getId(), timeShareMeasurables.getFkMeasurablesID(),timeShareMeasurables.getMeasurableQuantity(),timeShareMeasurables.getMeasurableUnit());
            timeShareMeasurablesRepository.save(timeShareMeasurables);
        });
        return saveTimeShare;
    }
}
