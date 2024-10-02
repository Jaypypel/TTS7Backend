package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import lombok.Data;


//new class created
@Data
@Entity
public class DailyTimeShareMeasurables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "timeshareId", referencedColumnName = "id")
    private TimeShare fkTimeShareId;//check

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "measurablesId", referencedColumnName = "id")
    private Measurables fkMeasurablesID;//check

    private Long measurableQuantity;

    private String measurableUnit;
}
