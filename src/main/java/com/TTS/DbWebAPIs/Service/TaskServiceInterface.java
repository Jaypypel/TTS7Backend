package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Task;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.TaskName;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TaskServiceInterface {
    List<String> getTaskNameList(String userId);

    Integer getTaskCount(String username, LocalDate startDate, LocalDate endDate);

    Integer getTaskFreqeuncyCount(String username, LocalDate startDate, LocalDate endDate);

    Task addTask(String username, Long activtyId, String taskName, LocalTime createdOn);
}
