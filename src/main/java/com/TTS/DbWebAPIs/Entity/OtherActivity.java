package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
//created new class
@Data
@Entity
@Table(name = "OTHER_ACTIVITY")
public class OtherActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String createdOn;

}
