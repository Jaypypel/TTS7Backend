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
@Table(name = "TIME_SHARE_MEASURABLES")
public class TimeShareMeasurables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    //One to One
    @ManyToOne
    @JoinColumn(name = "time_share_id", referencedColumnName = "id")
    private TimeShare fkTimeShareId;//check

//    @ManyToOne()
//    private DelegationMeasurables fkDelegationMeasurablesId;//check

    //One to One
    @ManyToOne
    @JoinColumn(name = "measurableId", referencedColumnName = "id")
    private Measurables fkMeasurablesID;

    private Long measurableQuantity;

    private String measurableUnit;

}
