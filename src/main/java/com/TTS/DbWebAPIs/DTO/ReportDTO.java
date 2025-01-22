package com.TTS.DbWebAPIs.DTO;

import com.TTS.DbWebAPIs.Entity.Report;

public class ReportDTO {
    private String timeShareDate;
    private String activityName;
    private String taskName;
    private String projectCode;
    private String projectName;
    private String startTime;
    private String endTime;
    private String consumedTime;
    private String measurableName;
    private String measurableQty;
    private String measurableUnit;
    private String description;

    public ReportDTO( String timeShareDate,String activityName,String taskName,String projectCode, String projectName,
                      String startTime,String endTime,  String consumedTime,String measurableName,
                      String measurableQty, String measurableUnit, String description) {
        this.timeShareDate = timeShareDate;
        this.activityName = activityName;
        this.taskName = taskName;
        this.projectCode = projectCode;
        this.projectName = projectName;
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

    public String getMeasurableName() {
        return measurableName;
    }

    public void setMeasurableName(String measurableName) {
        this.measurableName = measurableName;
    }

    public String getMeasurableQty() {
        return measurableQty;
    }

    public void setMeasurableQty(String measurableQty) {
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


//    public static Report convertToReport(ReportDTO reportDTO){
//        Report report = new Report();
//        report.setTimeShareDate(reportDTO.getTimeShareDate());
//        report.setActivityName(reportDTO.getActivityName());
//        report.setTaskName(reportDTO.getTaskName());
//        report.setProjectCode(reportDTO.getProjectCode());
//        report.setProjectName(reportDTO.getProjectName());
//        report.setStartTime(reportDTO.getStartTime());
//        report.setEndTime(reportDTO.getEndTime());
//        report.setTimeDifference(reportDTO.getConsumedTime());
//        report.setMeasurableName(reportDTO.getMeasurableName());
//        report.setMeasurableQty(Long.valueOf(reportDTO.getMeasurableQty()));
//        report.setMeasurableUnit(reportDTO.getMeasurableUnit());
//        report.setDescription(reportDTO.getDescription());
//        return report;
//    }
}
