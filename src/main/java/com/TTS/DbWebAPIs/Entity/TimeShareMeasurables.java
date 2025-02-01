package com.TTS.DbWebAPIs.Entity;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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
    @NotNull
    @Valid
    private TimeShare fkTimeShareId;//check

//    @ManyToOne()
//    private DelegationMeasurables fkDelegationMeasurablesId;//check

    //One to One
    @ManyToOne
    @JoinColumn(name = "measurableId", referencedColumnName = "id")
    @Valid
    @NotNull
    private Measurables fkMeasurablesID;

    @Max(value = 1000, message = "Quantity of a measurable can not exceed 1000 characters")
    @Positive
    private Long measurableQuantity;

    @NotBlank
    @Size(max = 30, message = "Unit of measurable can not exceed to 30 characters")
    private String measurableUnit;

}
