package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DecimalStyle;

@Data
@Entity
@Table(name = "TIME_SHARE_OTHER_ACTIVITY")
public class TimeShareOtherActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;//check

    private String activity;


    private String Date;

    private String startTime;

    private String endTime;

    private String timeDifference;

    private String Description;

    private String createdOn;



}
