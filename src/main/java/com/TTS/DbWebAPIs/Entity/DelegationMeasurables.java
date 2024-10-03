package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DelegationMeasurables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "taskHandler", referencedColumnName = "id")
    private TaskManagement fkTaskManagementID;//check

    @OneToOne
    @JoinColumn(name = "measurableId", referencedColumnName = "id")
    private Measurables fkMeasurableId;//check

//    @OneToOne
//    @JoinColumn(name = "id", referencedColumnName = "id")
//    private TimeShareMeasurables timeShareMeasurable;//check

    private Long expectedMeasurableQuantity;

    private String measurableUnit;

    private Long actualMeasurableQuantity;


}
