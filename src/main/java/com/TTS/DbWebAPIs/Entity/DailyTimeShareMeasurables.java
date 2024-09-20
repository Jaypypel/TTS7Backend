package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;

//new class created
@Data
@Entity
public class DailyTimeShareMeasurables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    @JoinColumn(name = "timeshareId", referencedColumnName = "id")
    private TimeShare fkTimeShareId;

    /* @OneToOne
     @JoinColumn(name = "delgtMsrblesId", referencedColumnName = "id")
     private DelegationMeasurables fkDelegationMeasurablesId;
 */
    @OneToOne
    @JoinColumn(name = "measurablesId", referencedColumnName = "id")
    private Measurables fkMeasurablesID;

    private Long measurableQuantity;

    private String measurableUnit;
}
