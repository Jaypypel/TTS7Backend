package com.TTS.DbWebAPIs.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
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
    private String consumedTime;
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
        this.consumedTime = consumedTime;
        this.measurableName = measurableName;
        this.measurableQty = measurableQty;
        this.measurableUnit = measurableUnit;
        this.description = description;


    }
    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getConsumedTime() {
        return consumedTime;
    }

    public void setConsumedTime(String consumedTime) {
        this.consumedTime = consumedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeasurableName() {
        return measurableName;
    }

    public void setMeasurableName(String measurableName) {
        this.measurableName = measurableName;
    }

    public Long getMeasurableQty() {
        return measurableQty;
    }

    public void setMeasurableQty(Long measurableQty) {
        this.measurableQty = measurableQty;
    }

    public String getMeasurableUnit() {
        return measurableUnit;
    }

    public void setMeasurableUnit(String measurableUnit) {
        this.measurableUnit = measurableUnit;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTimeShareDate() {
        return timeShareDate;
    }

    public void setTimeShareDate(String timeShareDate) {
        this.timeShareDate = timeShareDate;
    }
}
