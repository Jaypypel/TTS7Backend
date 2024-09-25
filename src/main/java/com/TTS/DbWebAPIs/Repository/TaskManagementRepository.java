package com.TTS.DbWebAPIs.Repository;

import com.TTS.DbWebAPIs.Entity.Task;
import com.TTS.DbWebAPIs.Entity.TaskManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TaskManagementRepository  extends JpaRepository<TaskManagement,Long> {

    @Query("SELECT tm from TaskManagement tm WHERE tm.taskReceivedUserID.id =: truId AND tm.status =: status")
    List<TaskManagement> findByTaskReceivedUserIDAndStatus(Long truId, String status);

    @Query("SELECT tm from TaskManagement tm WHERE tm.taskOwnerUserID.id =: touId AND tm.status =: status")
    List<TaskManagement> findByTaskOwnerUserIDAndStatus(Long touId, String status);

    @Query("SELECT tm from TaskManagement tm WHERE tm.taskReceivedUserId.id =: truId")
    List<TaskManagement> findByTasKReceivedUserId(Long truId);

    @Query("SELECT tm from TaskManagement tm WHERE tm.taskOwnerUserId.id =: truId")
    List<TaskManagement> findByTasKOwnerUserId(Long truId);

   //@Query("SELECT tm from TaskManagement tm WHERE tm.")
}
