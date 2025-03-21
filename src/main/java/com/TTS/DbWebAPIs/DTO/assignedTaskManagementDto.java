package com.TTS.DbWebAPIs.DTO;

import lombok.Data;

import java.util.List;

@Data
public class assignedTaskManagementDto {
    private TaskManagementDTO dto;
    private List<AssociatedMeasurableDto> associatedMeasurableDtos;
}
