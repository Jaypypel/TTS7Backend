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
    @JoinColumn(name = "taskOwner", referencedColumnName = "id")
    private   User FKTaskOwnerUserID;//check

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "taskReceiver", referencedColumnName = "id"
    )
    private   User FKTaskReceivedUserID;//check

    @OneToOne(mappedBy = "FKTaskManagementId")
    private TimeShare timeShareAssociated;//check

    //private  Long ActivityId;

    private  String ActivityName;

  //  private  Long Task_ID;

    private String taskName;

    private Long projectId;

    private  String projectName;

    private LocalDateTime expectedDate;
    //added new expectedTime
    private LocalTime expectedTime;

    private String expectedTotalTime;

    private String description;

    private LocalTime TaskAssignedOn;

    private String  actualTotalTime;

    private LocalTime taskSeenOn;

    private LocalTime taskCompletedOn;

    private LocalTime taskAcceptedOn;

    private String status;


    @OneToOne(mappedBy = "fkTaskManagementID")
    private DelegationMeasurables delegationMeasurablesAssociated;//check

}
