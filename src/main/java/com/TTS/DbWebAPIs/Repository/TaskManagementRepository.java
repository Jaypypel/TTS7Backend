package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.Task;
import com.TTS.DbWebAPIs.Entity.TaskManagement;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface TaskManagementRepository  extends JpaRepository<TaskManagement,Long> {


    @Query("SELECT tm from TaskManagement tm WHERE tm.taskReceivedUserID.username =:username AND tm.status =:status")
    List<TaskManagement> findByUserUsernameAndStatus(@Param("username") String username,@Param("status") String status);

    @Query(value = "SELECT COUNT(*) FROM  task_management  WHERE task_owner_username =:username AND status  =:status",nativeQuery = true)
    Integer CountByUserUsernameAndStatus(@Param("username") String  username,@Param("status") String status);


    @Query("SELECT tm from TaskManagement tm WHERE tm.taskOwnerUserID.username =:username AND tm.status =:status")
    List<TaskManagement> findByTaskOwnerUserIdAndStatus(String username, String status);


    @Query("SELECT tm from TaskManagement tm WHERE tm.taskReceivedUserID.username =:username")
    List<TaskManagement> findByTaskReceivedUserId(String username);


    @Query("SELECT tm from TaskManagement tm WHERE tm.taskOwnerUserID.username =:username")
    List<TaskManagement> findByTaskOwnerUserId(String username);

    @Query("SELECT MAX(t.id) FROM TaskManagement t")
    Long findMaxId();

    @Query("SELECT tm.actualTotalTime as ActualTotalTime FROM TaskManagement tm WHERE tm.id =:assignedTaskId")
    String getActualTotalFromId(Long assignedTaskId);
   //@Query("SELECT tm from TaskManagement tm WHERE tm.")
}
