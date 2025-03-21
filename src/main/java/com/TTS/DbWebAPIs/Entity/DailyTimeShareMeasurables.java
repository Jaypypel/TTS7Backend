package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;


//new class created
@Data
@Entity
@Table(name = "DAILY_TIME_SHARE_MEASURABLE")
public class DailyTimeShareMeasurables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "daily_time_share_id", referencedColumnName = "id")
//    @Valid
//    @NotNull
    private DailyTimeShare dailyTimeShare;//check

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "measurable_id", referencedColumnName = "id")
//    @Valid
//    @NotNull
    private Measurables fkMeasurablesID;//check

    @Max(value = 1000, message = "Quantity of a measurable can not exceed 1000 characters")
    @Positive
    private Long measurableQuantity;

    @NotBlank
    @Size(max = 30, message = "Unit of measurable can not exceed to 30 characters")
    private String measurableUnit;
}
