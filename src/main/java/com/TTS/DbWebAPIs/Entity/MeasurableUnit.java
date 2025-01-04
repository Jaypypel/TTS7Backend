package com.TTS.DbWebAPIs.Entity;


import jakarta.persistence.*;
import lombok.Data;
//create mew class
@Data
@Entity
@Table(name = "MEASURABLE_UNIT")
public class MeasurableUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


}
