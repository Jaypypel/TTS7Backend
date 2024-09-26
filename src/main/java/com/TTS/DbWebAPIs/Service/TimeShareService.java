package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Repository.TimeShareRepository;
import com.TTS.DbWebAPIs.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor

public class TimeShareService implements TimeShareServiceInterface{

    private final UserRepository userRepository;
    private  final TimeShareRepository timeShareRepository;

    @Override
    public List<TimeShare> getTimeShareList(Long userId, LocalDate startDate, LocalDate endDate) {
     User user   = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("user not found"));
     List<TimeShare> timeShareList = timeShareRepository.findTimeSharesByUserIdAndDateRange(user,startDate,endDate );
      return timeShareList;
    }
}
