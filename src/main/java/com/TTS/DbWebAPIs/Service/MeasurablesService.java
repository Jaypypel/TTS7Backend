package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.Measurables;
import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Exceptions.NotFoundException;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.MeasurablesIdAndName;
import com.TTS.DbWebAPIs.Repository.MeasurablesRepository;
import com.TTS.DbWebAPIs.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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
    public List<Measurables> getDTSMeasurablesList(Long dtsId) throws SQLException {
        return measurablesRepository.findMeasurablesById(dtsId);
    }

    @Override
    public List<Measurables> getMeasurableList() throws SQLException {
        return measurablesRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }


    @Override
    public Integer getMeasurableCount(String username, LocalDate startDate, LocalDate endDate) throws SQLException {
        return measurablesRepository.findByUserAndDateRange(username,startDate,endDate);
    }

    @Override
    public List<String> getMeasurableListForUsername(String username)  throws SQLException{
        return measurablesRepository.findByUsername(username);
    }

    @Override
    public Measurables addMeasurable(String username, String measurableName, String createdOn) throws SQLException {
        Measurables measurables = new Measurables();
        userRepository.findByUsername(username).ifPresentOrElse(measurables::setUser,()-> new NotFoundException("user name not found"));
        measurables.setName(measurableName);
        measurables.setCreatedOn(createdOn);
        measurablesRepository.save(measurables);
        return measurables;
    }
}
