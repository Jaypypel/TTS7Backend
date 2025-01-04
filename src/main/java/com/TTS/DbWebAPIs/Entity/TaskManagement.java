package com.TTS.DbWebAPIs.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
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
    private  long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "taskOwnerUsername", referencedColumnName = "username")
    private   User taskOwnerUserID;//check

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "taskReceiverUsername", referencedColumnName = "username"
    )
    private   User taskReceivedUserID;//check

//    @OneToOne(mappedBy = "fkTaskManagementId")
//    private TimeShare timeShareAssociated;//check

    //private  Long ActivityId;

    @Column(name = "activity_name")
    private  String activityName;

  //  private  Long Task_ID;
  @Column(name = "task_name")
    private String taskName;

    @Column(name = "project_code")
    private String projectCode;

    @Column(name = "project_name")
    private  String projectName;



    @Column(name = "expected_date")
    private LocalDate expectedDate;
    //added new expectedTime
    @Column(name = "expected_time")
    private String expectedTime;

    @Column(name = "expected_total_time")
    private String expectedTotalTime;

    @Column(name = "description")
    private String description;

    @Column(name = "task_assigned_on")
//    @JsonFormat(pattern = "dd-MM-yyyy hh:mm a")
    private String taskAssignedOn;

    @Column(name = "actual_total_time")
    private String  actualTotalTime;

    private String taskSeenOn;

    @Column(name = "task_completed_on")
    private String taskCompletedOn;

    @Column(name = "task_accepted_on")
    private String taskAcceptedOn;

    @Column(name = "task_processed_on")
    private String taskProcessedOn;

    @Column(name = "task_approved_on")
    private String tasKApprovedOn;

    @Column(name = "status")
    private String status;

//
//    @OneToOne(mappedBy = "fkTaskManagementID")
//    private DelegationMeasurables delegationMeasurablesAssociated;//check

}
