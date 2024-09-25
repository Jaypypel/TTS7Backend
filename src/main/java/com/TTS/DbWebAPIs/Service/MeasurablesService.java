package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Repository.MeasurablesRepository;

import java.util.ArrayList;
import java.util.List;

public class MeasurablesService implements MeasurablesServiceInterface{
    MeasurablesRepository measurablesRepository;


    /*need to check whether getting list of dtsMeasurables or measurables since in the TTSDailyShareFragment Measurable model is different from than the db measurable model*/
    @Override
    public List<Measurables> getMeasurablesList(Long dtsId) {
        List<Measurables>  dailyTimeShareMeasurables = new ArrayList<>();
        Measurables dailyTimeShareMeasurable;
        while(measurablesRepository.getMeasurables(dtsId)!=null){
            dailyTimeShareMeasurable = measurablesRepository.getMeasurables(dtsId);
            dailyTimeShareMeasurables.add(dailyTimeShareMeasurable);
        }
        return dailyTimeShareMeasurables;
    }
}
