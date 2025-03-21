package com.TTS.DbWebAPIs.DTO;

import lombok.Data;

import java.sql.Time;
import java.util.List;

@Data
public class AddTimeshareDto {
    private TimeShareDTO timeShareDto;
    private List<AssociatedMeasurableDto> timeshareMeasurables;
}
