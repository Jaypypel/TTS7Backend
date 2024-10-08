package com.TTS.DbWebAPIs.Entity;

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
    private LocalTime createdOn;

    @ManyToOne
    @JoinColumn(name="userid", referencedColumnName = "username")
    private User user;
}
