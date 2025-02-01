package com.TTS.DbWebAPIs.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DELEGATION_MEASURABLES")
public class DelegationMeasurables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "taskManagementId", referencedColumnName = "id")
    @JsonIgnore
    @NotNull
    @Valid
    private TaskManagement fkTaskManagementID;//check

    @ManyToOne
    @JoinColumn(name = "measurableId", referencedColumnName = "id")
    @NotNull
    @Valid
    private Measurables fkMeasurableId;//check

//    @OneToOne
//    @JoinColumn(name = "id", referencedColumnName = "id")
//    private TimeShareMeasurables timeShareMeasurable;//check
    @Max(value = 1000, message = "Quantity of a measurable can not exceed 1000 value")
    @Positive
    private Long expectedMeasurableQuantity;

    @NotBlank
    @Size(max = 30, message = "Unit of measurable can not exceed to 30 characters")
    private String measurableUnit;

    @Max(value = 1000, message = "Quantity of a measurable can not exceed 1000 value")
    @Positive
    private Long actualMeasurableQuantity;


}
