package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class TaskManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "taskOwner", referencedColumnName = "username")
    private   User taskOwnerUserID;//check

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "taskReceiver", referencedColumnName = "username"
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
    private LocalDateTime expectedDate;
    //added new expectedTime
    @Column(name = "expected_time")
    private LocalTime expectedTime;

    @Column(name = "expected_total_time")
    private String expectedTotalTime;

    @Column(name = "description")
    private String description;

    @Column(name = "task_assigned_on")
    private LocalTime taskAssignedOn;

    @Column(name = "actual_total_time")
    private String  actualTotalTime;

    private LocalTime taskSeenOn;

    @Column(name = "task_completed_on")
    private LocalTime taskCompletedOn;

    @Column(name = "task_accepted_on")
    private LocalTime taskAcceptedOn;

    @Column(name = "task_processed_on")
    private LocalTime taskProcessedOn;

    @Column(name = "taskapproved_on")
    private LocalTime tasKApprovedOn;

    @Column(name = "status")
    private String status;


    @OneToOne(mappedBy = "fkTaskManagementID")
    private DelegationMeasurables delegationMeasurablesAssociated;//check

}
