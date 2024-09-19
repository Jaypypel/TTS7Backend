package com.TTS.DbWebAPIs.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TimeShareMeasurables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    @JoinColumn(name = "timeshareId", referencedColumnName = "id")
    private Long fkTimeShareId;

    @OneToOne
    @JoinColumn(name = "delgtMsrblesId", referencedColumnName = "id")
    private Long fkDelegationMeasurablesId;

    @OneToOne
    @JoinColumn(name = "measurablesId", referencedColumnName = "id")
    private Long fkMeasurablesID;

    private Long measurableQuantity;

    private String measurableUnit;

}
