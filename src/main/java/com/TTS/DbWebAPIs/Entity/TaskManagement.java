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

    @ManyToOne
    @JoinColumn(name = "taskOwner", referencedColumnName = "id")
    private   User FKTaskOwnerUserID;

    @ManyToOne
    @JoinColumn(
            name = "task"
    )
    private   User FKTaskReceivedUserID;

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

}
