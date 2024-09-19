package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    private String FK_ACTIVITY_ID;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName= "username")
    private String FK_User_ID;
}
