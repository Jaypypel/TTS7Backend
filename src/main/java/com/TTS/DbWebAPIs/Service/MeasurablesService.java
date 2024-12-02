package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.MeasurablesIdAndName;
import com.TTS.DbWebAPIs.Repository.MeasurablesRepository;
import com.TTS.DbWebAPIs.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasurablesService implements MeasurablesServiceInterface{

   private final  MeasurablesRepository measurablesRepository;
   private  final UserRepository userRepository;


    /*need to check whether getting list of dtsMeasurables or measurables since in the TTSDailyShareFragment Measurable model is different from than the db measurable model
    * same*/
    @Override
    public List<Measurables> getDTSMeasurablesList(Long dtsId) {
        List<Measurables>  dailyTimeShareMeasurables = measurablesRepository.findMeasurablesById(dtsId);

//        MeasurablesIdAndName dailyTimeShareMeasurable;
//        while(measurablesRepository.getMeasurables(dtsId)!=null){
//            dailyTimeShareMeasurable = measurablesRepository.getMeasurables(dtsId);
//            dailyTimeShareMeasurables.add(dailyTimeShareMeasurable);
//        }
        return dailyTimeShareMeasurables;
    }

    @Override
    public List<Measurables> getMeasurableList() {
        return measurablesRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

//    @Override
//    public List<Measurables> getAllocatedMeasurableList(Long taskId) {
//        return measurablesRepository.findAllocatedMeasurablesByTaskId(taskId);
//    }

    @Override
    public Integer getMeasurableCount(String username, LocalDate startDate, LocalDate endDate) {
        return measurablesRepository.findByUserAndDateRange(username,startDate,endDate);
    }

    @Override
    public List<String> getMeasurableListForUsername(String username) {
        return measurablesRepository.findByUsername(username);
    }

    @Override
    public Measurables addMeasurable(String username, String measurableName, String createdOn) {
        User user = userRepository.findByUsername(username);
        if(user.getUsername().isEmpty() || user.getUsername().isBlank()){
            throw  new RuntimeException("user not found");
        }
        Measurables measurables = new Measurables();
        measurables.setUser(user);
        measurables.setName(measurableName);
        measurables.setCreatedOn(createdOn);
        measurablesRepository.save(measurables);
        return measurables;
    }
}
