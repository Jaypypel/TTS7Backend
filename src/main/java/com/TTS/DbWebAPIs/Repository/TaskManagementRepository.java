package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.Task;
import com.TTS.DbWebAPIs.Entity.TaskManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskManagementRepository  extends JpaRepository<TaskManagement,Long> {

    @Query("SELECT tm from TaskManagement tm WHERE tm.taskReceivedUserID.id =: truId AND tm.status =: status")
    List<TaskManagement> findByIdAndStatus(Long truId, String status);

    @Query("SELECT tm from TaskManagement tm WHERE tm.taskOwnerUserID.id =: touId AND tm.status =: status")
    List<TaskManagement> findByTaskOwnerUserIdAndStatus(Long touId, String status);

    @Query("SELECT tm from TaskManagement tm WHERE tm.taskReceivedUserID.id =: truId")
    List<TaskManagement> findByTaskReceivedUserId(Long truId);

    @Query("SELECT tm from TaskManagement tm WHERE tm.taskOwnerUserID.id =: truId")
    List<TaskManagement> findByTaskOwnerUserId(Long truId);

    @Query("SELECT MAX(t.id) FROM TaskManagement t")
    Long findMaxId();

    @Query("SELECT tm.actualTotalTime as ActualTotalTime FROM TaskManagement tm WHERE tm.id =: assignedTaskId")
    String getActualTotalFromId(Long assignedTaskId);
   //@Query("SELECT tm from TaskManagement tm WHERE tm.")
}
