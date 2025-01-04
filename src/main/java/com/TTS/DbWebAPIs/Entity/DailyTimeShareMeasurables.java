package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.*;
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
    private DailyTimeShare dailyTimeShare;//check

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "measurable_id", referencedColumnName = "id")
    private Measurables fkMeasurablesID;//check

    private Long measurableQuantity;

    private String measurableUnit;
}
