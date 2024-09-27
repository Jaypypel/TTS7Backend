package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
public class DailyTimeShare {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateOfTimeShare;

    private String projectCode;

    private String projectName;

    private String activityName;

    private String taskName;

    private LocalTime startTime;

    private LocalTime endTime;

    private String timeDifference;

    private String description;

    private LocalTime createdOn;

    @ManyToOne
    @JoinColumn(name="userID", referencedColumnName = "id")
    private User user;
}
