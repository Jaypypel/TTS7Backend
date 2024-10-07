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

    private  String activityName;

  //  private  Long Task_ID;

    private String taskName;


    private String projectCode;

    private  String projectName;

    private LocalDateTime expectedDate;
    //added new expectedTime
    private LocalTime expectedTime;

    private String expectedTotalTime;

    private String description;

    private LocalTime taskAssignedOn;

    private String  actualTotalTime;

    private LocalTime taskSeenOn;

    private LocalTime taskCompletedOn;

    private LocalTime taskAcceptedOn;

    private LocalTime taskProcessedOn;

    private LocalTime tasKApprovedOn;

    private String status;


    @OneToOne(mappedBy = "fkTaskManagementID")
    private DelegationMeasurables delegationMeasurablesAssociated;//check

}
