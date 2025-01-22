package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@ToString
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String timeShareDate;
    private String activityName;
    private String taskName;
    private String projectCode;
    private String projectName;
    private String startTime;
    private String endTime;
    private String timeDifference;
    private String measurableName;
    private Long measurableQty;
    private String measurableUnit;
    private String description;

    public Report(Long id, String timeShareDate,String projectCode, String projectName,String activityName,
                  String taskName, String startTime, String endTime, String consumedTime, String measurableName,
                  Long measurableQty, String measurableUnit, String description){
        this.id = id;
        this.timeShareDate = timeShareDate;
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.activityName = activityName;
        this.taskName = taskName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeDifference = consumedTime;
        this.measurableName = measurableName;
        this.measurableQty = measurableQty;
        this.measurableUnit = measurableUnit;
        this.description = description;
    }
}
