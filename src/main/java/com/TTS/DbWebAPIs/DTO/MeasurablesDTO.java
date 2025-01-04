package com.TTS.DbWebAPIs.DTO;

public class MeasurablesDTO {
    private Long id;
    private String name;
    private String measurableQty;
    private String MeasurableUnit;

    public MeasurablesDTO(Long id,String name) {
        this.id = id;
        this.name = name;
        this.measurableQty = "";
        this.MeasurableUnit = "";

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setMeasurableName(String measurableName) {
        this.name = name;
    }

    public String getMeasurableQty() {
        return measurableQty;
    }

    public void setMeasurableQty(String measurableQty) {
        this.name = name;
    }

    public String getMeasurableUnit() {
        return MeasurableUnit;
    }

    public void setMeasurableUnit(String getMeasurableQty) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  this.id + "-" + this.name;
    }
}
