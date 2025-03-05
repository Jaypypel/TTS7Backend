package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.*;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Exceptions.UserNotFoundException;
import com.TTS.DbWebAPIs.Repository.*;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
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

    @Cacheable("timeshareByUser")
    @Override
    public List<TimeShare> getTimeShareList(String username, LocalDateTime startDate, LocalDateTime endDate)
            throws DatabaseException, UserNotFoundException {
        User existingUser = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found by username :" +username));
        return timeShareRepository
                .findTimeSharesByUserIdAndDateRange(existingUser.getUsername(),startDate,endDate );
    }

    @Cacheable("timeshareByTaskId")
    @Override
    public List<TimeShare> getTimeShareLists(Long taskId)
            throws NotFoundException, DatabaseException {
        TaskManagement taskManagement = taskManagementRepository
                .findById(taskId)
                .orElseThrow(() -> new NotFoundException("task not found "));
        return timeShareRepository.findTimeShareByTaskManagementId(taskManagement.getId());

    }

    @Override
    public Long getMaxTimeShareId() throws DatabaseException{
        return (timeShareRepository.findMaxTimeShareId() != null) ? (timeShareRepository.findMaxTimeShareId() + 1) : 0;
    }


    @CacheEvict(allEntries = true,value = {"timeshareByTaskId","timeshareByUser"})
    @Transactional
    @Override
    public TimeShare addTimeShare(TimeShare timeShare) throws DatabaseException{
       // TaskManagement taskManagement = taskManagementRepository.findById(timeShare.getFkTaskManagementId().getId()).orElseThrow(()->new RuntimeException("task not found"));
        return timeShareRepository.save(timeShare);
    }
}
