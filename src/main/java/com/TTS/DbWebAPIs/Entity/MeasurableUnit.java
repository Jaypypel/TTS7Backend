package com.TTS.DbWebAPIs.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
//create mew class
@Data
@Entity
@Table(name = "MEASURABLE_UNIT")
public class MeasurableUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50, message = "The unit can not exceed 50 characters" )
    private String name;


}
