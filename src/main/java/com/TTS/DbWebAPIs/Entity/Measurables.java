package com.TTS.DbWebAPIs.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Measurables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //added new

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "username")
    private User user;

//    @OneToOne(mappedBy = "fkMeasurablesID", cascade = CascadeType.MERGE)
//    private DailyTimeShareMeasurables measurablesAssociated;//ccheck
//
//    @OneToOne(mappedBy = "fkMeasurableId")
//    private DelegationMeasurables delegationMeasurablesAssociated;//check
//
//    @OneToOne(mappedBy = "fkMeasurablesID")
//    private TimeShareMeasurables timeShareMeasurablesAssociated;//check

    private LocalTime createdOn;


}
