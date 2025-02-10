package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.Project;
import com.TTS.DbWebAPIs.Repository.InterfaceProjections.ProjectCode;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    @Cacheable
    @Query("SELECT DISTINCT(p.projectCode) FROM Project p WHERE p.name = :name")
    String findByProjectName(String name);

    Project findByProjectCode(String projectCode);

    @Query("SELECT p.projectCode FROM Project p")
    List<String> findByProjectCodeList();

//    @Query("SELECT COUNT(DISTINCT(dts.projectCode)) FROM DailyTimeShare dts WHERE dts.user.username = :username AND dts.dateOfTimeShare BETWEEN  :startDate AND :endDate")
//@Query("SELECT COUNT(DISTINCT(dts.projectCode)) FROM DailyTimeShare dts WHERE LOWER(dts.user.username) = LOWER(:username) AND dts.dateOfTimeShare BETWEEN :startDate AND :endDate")
@Query(value = "SELECT COUNT(DISTINCT(dts.project_code)) FROM daily_time_share dts WHERE LOWER(dts.userid) = LOWER(:username) AND dts.date_of_time_share BETWEEN :startDate AND :endDate", nativeQuery = true)
    Integer findByUserIdAndDateRange(String username, LocalDate startDate, LocalDate endDate);

    @Query("SELECT p.name FROM Project p")
    List<String> findByName();

    @Query("SELECT COUNT(d.projectName) FROM DailyTimeShare d WHERE d.user.id =:userId" +
            "  AND d.dateOfTimeShare BETWEEN :startDate AND :endDate")
    Integer findByIdAndDateRange(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    @Transactional
    Project findByName(String projectname);

}