package com.TTS.DbWebAPIs.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "taskHandler", referencedColumnName = "id")
    @JsonIgnore
    private TaskManagement fkTaskManagementID;//check

    @ManyToOne
    @JoinColumn(name = "measurableId", referencedColumnName = "id")
    private Measurables fkMeasurableId;//check

//    @OneToOne
//    @JoinColumn(name = "id", referencedColumnName = "id")
//    private TimeShareMeasurables timeShareMeasurable;//check

    private Long expectedMeasurableQuantity;

    private String measurableUnit;

    private Long actualMeasurableQuantity;


}
