package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.OtherActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OtherActivityRepository  extends JpaRepository<OtherActivity,Long> {
    @Query("SELECT  oa.name FROM OtherActivity oa")
    List<String> findOtherActivityNames();
}
