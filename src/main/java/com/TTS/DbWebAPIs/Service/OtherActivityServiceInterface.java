package com.TTS.DbWebAPIs.Service;

import com.TTS.DbWebAPIs.Entity.OtherActivity;
import com.TTS.DbWebAPIs.Exceptions.DatabaseException;

import javax.print.attribute.standard.MediaSize;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface OtherActivityServiceInterface {
    //need to return only name from otheractivity object rather object itself
    List<String> getOtherActivityList() throws DatabaseException;

    OtherActivity addOtherActivity(String otherActiName, String createdOn)  throws DatabaseException;

}
