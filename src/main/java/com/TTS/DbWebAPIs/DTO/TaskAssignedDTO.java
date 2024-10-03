package com.TTS.DbWebAPIs.DTO;

import com.TTS.DbWebAPIs.Entity.DelegationMeasurables;
import com.TTS.DbWebAPIs.Entity.TaskManagement;

import java.util.List;

public class TaskAssignedDTO {
    @Override
    public String toString() {
        return "TaskAssignedDTO{" +
                "taskManagement=" + taskManagement +
                ", delegationMeasurablesLlist=" + delegationMeasurablesLlist +
                '}';
    }

    TaskManagement taskManagement;
    List<DelegationMeasurables> delegationMeasurablesLlist;

    public TaskAssignedDTO(TaskManagement taskManagement, List<DelegationMeasurables> delegationMeasurablesLlist) {
        this.taskManagement = taskManagement;
        this.delegationMeasurablesLlist = delegationMeasurablesLlist;
    }

    public TaskManagement getTaskManagement() {
        return taskManagement;
    }

    public void setTaskManagement(TaskManagement taskManagement) {
        this.taskManagement = taskManagement;
    }

    public List<DelegationMeasurables> getDelegationMeasurablesLlist() {
        return delegationMeasurablesLlist;
    }

    public void setDelegationMeasurablesLlist(List<DelegationMeasurables> delegationMeasurablesLlist) {
        this.delegationMeasurablesLlist = delegationMeasurablesLlist;
    }
}
