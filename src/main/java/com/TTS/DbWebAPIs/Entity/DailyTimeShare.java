package com.TTS.DbWebAPIs.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
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

    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "time_difference")
    private String timeDifference;

    @Column(name = "description")
    private String description;


    @Column(name = "created_on")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createdOn;

    @ManyToOne
    @JoinColumn(name="userid", referencedColumnName = "username")
    private User user;
}
