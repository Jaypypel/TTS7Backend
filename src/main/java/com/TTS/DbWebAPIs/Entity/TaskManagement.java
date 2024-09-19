package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class TaskManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long ID;

    @ManyToOne
    @JoinColumn(name = "taskOwner", referencedColumnName = "username")
    private   Long FKTaskOwnerUserID;

    @ManyToOne
    @JoinColumn(
            name = "task"
    )
    private   String FKTaskReceivedUserID;

    private  Long ActivityId;

    private  String ActivityName;

    private  Long Task_ID;

    private String taskName;

    private Long ProjectId;

    private  String ProjectName;

    private Date ExpectedDate;

    private String expectedTotalTime;

    private String description;

    private LocalTime TaskAssignedOn;

    private String  actualTotalTime;

    private LocalTime taskSeenOn;

    private LocalTime taskCompletedOn;

    private LocalTime taskAcceptedOn;

    private String status;

}
