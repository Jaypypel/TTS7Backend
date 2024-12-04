package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DecimalStyle;

@Data
@Entity
public class TimeShareOtherActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "username")
    private User userId;//check

    private String activity;


    private LocalDateTime Date;

    private LocalTime startTime;

    private LocalTime endTime;

    private String timeDifference;

    private String Description;

    private String createdOn;



}
