package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Task;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.TaskName;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TaskServiceInterface {
    List<TaskName> getTaskNameList(String userId);

    Integer getTaskCount(Long userId, LocalDate startDate, LocalDate endDate);

    Integer getTaskFreqeuncyCount(Long userId, LocalDate startDate, LocalDate endDate);

    Task addTask(Long userId, Long activtyId, String taskName, LocalTime createdOn);
}
