package com.TTS.DbWebAPIs.DTO;

public class MeasurablesDTO {
    private Long id;
    private String measurableName;
    private String measurableQty;
    private String MeasurableUnit;

    public MeasurablesDTO(String measurableName,Long id) {
        this.measurableName = measurableName;
        this.measurableQty = "";
        this.MeasurableUnit = "";
        this.id = id;
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

    public String getMeasurableQty() {
        return measurableQty;
    }

    public void setMeasurableQty(String measurableQty) {
        this.measurableQty = measurableQty;
    }

    public String getMeasurableUnit() {
        return MeasurableUnit;
    }

    public void setMeasurableUnit(String getMeasurableQty) {
        this.MeasurableUnit = getMeasurableQty;
    }

    @Override
    public String toString() {
        return "MeasurablesDTO{" +
                "id=" + id +
                ", measurableName='" + measurableName + '\'' +
                ", measurableQty='" + measurableQty + '\'' +
                ", MeasurableUnit='" + MeasurableUnit + '\'' +
                '}';
    }
}
