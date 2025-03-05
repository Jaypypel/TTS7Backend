package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "TIME_SHARE")
public class TimeShare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "taskManagementId", referencedColumnName="id")
    private TaskManagement fkTaskManagementId;//check


//    @OneToOne(mappedBy = "fkTimeShareId")
//    private DailyTimeShareMeasurables dailyTimeShareMeasurables;//check
//
//    @OneToOne(mappedBy = "fkTimeShareId")
//    private TimeShareMeasurables timeShareMeasurablesAssociated;//check

    @FutureOrPresent
    private LocalDate dateOfTimeShare;

    private String startTime;

    @NotBlank
    private String endTime;

    private String timeDifference;

    private String description;


    private String createdOn;


}
/**/
