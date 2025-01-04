package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.OtherActivity;

import javax.print.attribute.standard.MediaSize;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface OtherActivityServiceInterface {
    //need to return only name from otheractivity object rather object itself
    List<String> getOtherActivityList() throws SQLException;

    OtherActivity addOtherActivity(String otherActiName, String createdOn)  throws SQLException;

}
