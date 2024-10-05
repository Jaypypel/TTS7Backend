package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.Project;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.ProjectCode;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.ProjectName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {

//    @Query("SELECT p FROM   Project p WHERE p.projectCode =:projectCode")
    Project findByProjectCode(String projectCode);

    @Query("SELECT p.id AS Id FROM Project p")
    List<ProjectCode> findById();

    @Query("SELECT COUNT(DISTINCT(dts.projectName)) FROM DailyTimeShare dts WHERE dts.user.id = :userId AND dts.dateOfTimeShare BETWEEN  :startDate AND :endDate")
    Integer findByUserIdAndDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT p.name AS Name FROM Project p")
    List<ProjectName> findByName();

    @Query("SELECT COUNT(d.projectName) FROM DailyTimeShare d WHERE d.user.id =:userId" +
            "  AND d.dateOfTimeShare BETWEEN :startDate AND :endDate")
    Integer findByIdAndDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    @Transactional
    Project findByName(String projectname);







}
