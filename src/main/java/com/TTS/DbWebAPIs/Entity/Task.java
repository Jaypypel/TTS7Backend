package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    private Activity FK_ACTIVITY_ID;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName= "id")
    private User FK_User_ID;

    //added
    private LocalTime createdOn;

}
