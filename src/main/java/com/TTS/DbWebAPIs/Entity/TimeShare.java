package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Data

public class TimeShare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    @JoinColumn(name = "taskHandler", referencedColumnName="id")
    private String FKTaskManagementId;

    private Date DateOfTimeShare;

    private LocalTime startTime;

    private LocalTime endTime;

    private String TimeDifference;

    private String description;

    private LocalTime createdOn;

}
