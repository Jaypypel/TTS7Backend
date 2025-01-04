package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
@Table(name = "TASK")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    private Activity activityAssociated;//check

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName= "username")
    private User user;//check
    //added
    private String createdOn;

}
