package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.OtherActivity;
import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;
import com.TTS.DbWebAPIs.Repository.OtherActivityRepository;
import com.TTS.DbWebAPIs.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OtherActivityService implements OtherActivityServiceInterface {

    private  final OtherActivityRepository otherActivityRepository;

    @Override
    public List<String> getOtherActivityList()  throws DatabaseException {
        return otherActivityRepository.findOtherActivityNames();
    }

    @Override
    public OtherActivity addOtherActivity(String otherActiName, String createdOn)  throws DatabaseException{
        OtherActivity otherActivity = new OtherActivity();
        otherActivity.setName(otherActiName);
        otherActivity.setCreatedOn(createdOn);
        return  otherActivityRepository.save(otherActivity) ;
    }
}
