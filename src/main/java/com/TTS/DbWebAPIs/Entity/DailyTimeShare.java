package com.TTS.DbWebAPIs.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity

@Table(name = "DAILY_TIME_SHARE")
public class DailyTimeShare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_of_time_share")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfTimeShare;

    @Column(name = "project_code")
    private String projectCode;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "activity_name")
    private String activityName;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "start_time")

    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "time_difference")
    private String timeDifference;

    @Column(name = "description")
    private String description;


    @Column(name = "created_on")
     private String createdOn;

    @ManyToOne
    @JoinColumn(name="username", referencedColumnName = "username")
    private User user;
}
