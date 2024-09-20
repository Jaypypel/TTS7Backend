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

    @OneToOne
    @JoinColumn(name = "taskHander", referencedColumnName = "id")
    private TaskManagement fkTaskManagementID;

    @OneToOne
    @JoinColumn(name = "measurableId", referencedColumnName = "id")
    private Measurables fkMeasurableId;

    private Long expectedMeasurableQuantity;

    private String measurableUnit;

    private Long actualMeasurableQuantity;


}
