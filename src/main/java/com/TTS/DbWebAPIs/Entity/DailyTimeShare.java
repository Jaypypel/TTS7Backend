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
    private Long Id;



    private LocalDateTime DateOfTimeShare;

    //add new
    private String projectCode;

    //add new
    private String projectName;

    //add new
    private String activityName;

    //add new
    private String taskName;

    private LocalTime startTime;

    private LocalTime endTime;

    private String TimeDifference;

    private String description;

    private LocalTime createdOn;

    @ManyToOne
    @JoinColumn(name="userID", referencedColumnName = "id")
    private User userid;
}
