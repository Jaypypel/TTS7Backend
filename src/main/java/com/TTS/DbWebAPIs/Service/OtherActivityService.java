package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.OtherActivity;
import com.TTS.DbWebAPIs.Entity.User;
import com.TTS.DbWebAPIs.Repository.OtherActivityRepository;
import com.TTS.DbWebAPIs.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OtherActivityService implements OtherActivityServiceInterface {

    private  final OtherActivityRepository otherActivityRepository;

    @Override
    public List<OtherActivity> getOtherActivityList() {
        return otherActivityRepository.findAll();
    }

    @Override
    public OtherActivity addOtherActivity(String otherActiName, LocalTime createdOn) {
        OtherActivity otherActivity = new OtherActivity();
        otherActivity.setName(otherActiName);
        otherActivity.setCreatedOn(createdOn);
        return  otherActivity;
    }
}
