package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.Project;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.ProjectCode;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.ProjectName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Query("SELECT p.id FROM   Project p WHERE p.name =: projectName")
    Integer findByProjectCode(String projectName);

    @Query("SELECT p.id AS Id FROM Project")
    List<ProjectCode> findById();

    @Query("SELECT COUNT(DISTINCT(p.id)) FROM Daily_Time_Share dts WHERE dts.user.id = :userId AND dts.dateOfTimeShare BETWEEN dts.startDate =: startDate AND dts.endDate")
    Integer getProjectCount(Long ProjectId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT p.name AS name FROM Project")
    List<ProjectName> findByName();

    @Query("SELECT COUNT(dts.projectName) FROM Daily_Time_Share dts WHERE dts.user.id =: userId" +
            "AND dts.dateOfTimeShare BETWEEN dts.startDate =: startDate AND =: endDate")
    Integer findByProjectName(Long userId, LocalDate startDate, LocalDate endDate);

    Project findByName(String projectname);



}
