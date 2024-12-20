package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class TimeShare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "taskHandler", referencedColumnName="id")
    private TaskManagement fkTaskManagementId;//check


//    @OneToOne(mappedBy = "fkTimeShareId")
//    private DailyTimeShareMeasurables dailyTimeShareMeasurables;//check
//
//    @OneToOne(mappedBy = "fkTimeShareId")
//    private TimeShareMeasurables timeShareMeasurablesAssociated;//check


    private String dateOfTimeShare;

    private String startTime;

    private String endTime;

    private String timeDifference;

    private String description;

    private String createdOn;


}
/**/
