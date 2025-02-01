package com.TTS.DbWebAPIs.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table(name = "TASK_MANAGEMENT")
public class TaskManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private  Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "taskOwnerUsername", referencedColumnName = "username")
    @Valid
    @NotNull
    private   User taskOwnerUserID;//check

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "taskReceiverUsername", referencedColumnName = "username"
    )
    @Valid
    @NotNull
    private   User taskReceivedUserID;//check

//    @OneToOne(mappedBy = "fkTaskManagementId")
//    private TimeShare timeShareAssociated;//check

    //private  Long ActivityId;

    @Column(name = "activity_name")
    @NotBlank(message = "activity name can not accept blank")
    @Size(max = 50, message = "activity name can not exceed 50 characters")
    private  String activityName;

  //  private  Long Task_ID;
    @Column(name = "task_name")
    @Size(max = 100, message = "task name can not exceed 100 characters")
    private String taskName;

    @Column(unique = true,name = "project_code")
    @NotBlank(message = "The project code can not be accepted blank")
    @Size(max = 50 , message = "The project code can not exceed 50 characters")
    private String projectCode;

    @Column(name = "project_name")
    @NotBlank
    @Size(max = 100, message = "The project code can not exceed 100 characters")
    private  String projectName;



    @Column(name = "expected_date")
    @FutureOrPresent
    @NotNull
    private LocalDate expectedDate;
    //added new expectedTime

    @Column(name = "expected_time")
    @NotBlank
    private String expectedTime;

    @Column(name = "expected_total_time")
    @NotBlank
    private String expectedTotalTime;

    @Column(name = "description")
    @NotBlank()
    @Size(max = 150, message = "description can not exceed 150 characters")
    private String description;

    @Column(name = "task_assigned_on")
//    @JsonFormat(pattern = "dd-MM-yyyy hh:mm a")
    @NotBlank
    private String taskAssignedOn;

    @Column(name = "actual_total_time")
    @NotBlank
    private String  actualTotalTime;

    @NotBlank
    private String taskSeenOn;

    @NotBlank
    @Column(name = "task_completed_on")
    private String taskCompletedOn;

    @NotBlank
    @Column(name = "task_accepted_on")
    private String taskAcceptedOn;

    @NotBlank
    @Column(name = "task_processed_on")
    private String taskProcessedOn;

    @NotBlank
    @Column(name = "task_approved_on")
    private String tasKApprovedOn;

    @NotBlank
    @Column(name = "status")
    private String status;

//
//    @OneToOne(mappedBy = "fkTaskManagementID")
//    private DelegationMeasurables delegationMeasurablesAssociated;//check

}
