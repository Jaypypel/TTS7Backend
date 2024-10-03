package com.TTS.DbWebAPIs.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

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
    private TimeShare fkTimeShareId;//check

    @OneToOne()
    private DelegationMeasurables fkDelegationMeasurablesId;//check

    @OneToOne
    @JoinColumn(name = "measurablesId", referencedColumnName = "id")
    private Measurables fkMeasurablesID;

    private Long measurableQuantity;

    private String measurableUnit;

}
