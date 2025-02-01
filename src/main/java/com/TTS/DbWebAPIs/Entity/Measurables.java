package com.TTS.DbWebAPIs.Entity;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEASURABLES")
public class Measurables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    @Size(max = 100 , message = "Name of Measurable can not exceed 100 characters")
    private String name;

    //added new

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    @Valid
    @NotNull
    private User user;

//    @OneToOne(mappedBy = "fkMeasurablesID", cascade = CascadeType.MERGE)
//    private DailyTimeShareMeasurables measurablesAssociated;//ccheck
//
//    @OneToOne(mappedBy = "fkMeasurableId")
//    private DelegationMeasurables delegationMeasurablesAssociated;//check
//
//    @OneToOne(mappedBy = "fkMeasurablesID")
//    private TimeShareMeasurables timeShareMeasurablesAssociated;//check

    private String createdOn;



}
