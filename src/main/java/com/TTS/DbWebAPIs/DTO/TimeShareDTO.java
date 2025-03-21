package com.TTS.DbWebAPIs.DTO;

import com.TTS.DbWebAPIs.Entity.TaskManagement;
import com.TTS.DbWebAPIs.Entity.TimeShare;
import com.TTS.DbWebAPIs.Entity.TimeShareMeasurables;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Data
public class TimeShareDTO {

    @Positive
    private Long taskHandlerId;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String date;

    @NotBlank
    private String startTime;

    @NotBlank
    private String endTime;

    @NotBlank
    private String timeDifference;


    @NotBlank
    @Size(max = 150, message = "description can not exceed 150 characters")
    private String description;

    @NotNull
    private String createdOn;

    public static TimeShare convertToTimeShare(TimeShareDTO timeShareDTO){


        TimeShare timeShare = new TimeShare();
        TaskManagement taskManagement = new TaskManagement();
        taskManagement.setId(timeShareDTO.getTaskHandlerId());
        timeShare.setFkTaskManagementId(taskManagement);
        LocalDate date = LocalDate.parse(timeShareDTO.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH));
        timeShare.setDateOfTimeShare(date);
        timeShare.setStartTime(timeShareDTO.getStartTime());
        timeShare.setEndTime(timeShareDTO.getEndTime());
        timeShare.setTimeDifference(timeShareDTO.getTimeDifference());
        timeShare.setDescription(timeShareDTO.getDescription());
        timeShare.setCreatedOn(timeShareDTO.getCreatedOn());
        return timeShare;
    }

    public static TimeShareDTO convertToTimeShareDTO(TimeShare timeShare){
        TimeShareDTO timeShareDto = new TimeShareDTO();
        timeShareDto.setTaskHandlerId(timeShare.getFkTaskManagementId().getId());
        timeShareDto.setDate(timeShare.getDateOfTimeShare().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        timeShareDto.setStartTime(timeShare.getStartTime());
        timeShareDto.setEndTime(timeShare.getEndTime());
        timeShareDto.setTimeDifference(timeShare.getTimeDifference());
        timeShareDto.setDescription(timeShare.getDescription());
        timeShareDto.setCreatedOn(timeShare.getCreatedOn());
        return timeShareDto;
    }
}
