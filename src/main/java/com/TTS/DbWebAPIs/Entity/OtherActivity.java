package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    @Size(max = 100, message = "The name of the other activity can not exceed 100 character")
    private String name;

    private String createdOn;

}
