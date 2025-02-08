package com.TTS.DbWebAPIs.DTO;

import com.TTS.DbWebAPIs.Entity.Measurables;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class MeasurablesDTO {
    private Long id;
    private String name;

    public MeasurablesDTO(Long id,String name) {
        this.id = id;
        this.name = name;
    }
    @Override
    public String toString() {
        return  this.id + "-" + this.name;
    }

    public static ArrayList<MeasurablesDTO> convertToMeasurableDTO(List<Measurables> measurables){
        ArrayList<MeasurablesDTO> measurableList = new ArrayList<>();
        MeasurablesDTO measurable;
        for(Measurables m: measurables){
            measurable = new MeasurablesDTO(m.getId(),m.getName());
            measurableList.add(measurable);
        }
        return measurableList;
    }
}
