package com.TTS.DbWebAPIs.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.springframework.web.jsf.FacesContextUtils;

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
//    @NotNull()
//    @FutureOrPresent
    private LocalDate dateOfTimeShare;

    @NotBlank
    @Column(name = "project_code")
    @Size(max = 50 )
    private String projectCode;

    @Size(max = 50)
    @NotBlank
    @Column(name = "project_name")
    private String projectName;

    @NotBlank
    @Column(name = "activity_name")
    @Size(max = 50)
    private String activityName;

    @Column(name = "task_name")
    @Size(max = 100)
    @NotBlank
    private String taskName;

    @NotBlank
    @Column(name = "start_time")
    private String startTime;

    @NotBlank
    @Column(name = "end_time")
    private String endTime;

    @NotBlank
    @Column(name = "time_difference")
    private String timeDifference;

    @Column(name = "description")
    @NotBlank()
    @Size(max = 150, message = "description can not exceed 150 characters")
    private String description;

    @NotNull
    @Column(name = "created_on")
    private String createdOn;

    @Valid
    @NotNull
     @ManyToOne()
    @JoinColumn(name="username", referencedColumnName = "username")
    private User user;
}
