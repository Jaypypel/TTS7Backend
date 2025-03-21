package com.TTS.DbWebAPIs.DTO;

import com.TTS.DbWebAPIs.Entity.DailyTimeShare;
import lombok.Data;

import java.util.List;

@Data
public class addDailyTimeShareDto {
    private DailyTimeShare dailyTimeShare;
    private List<AssociatedMeasurableDto> associatedMeasurableDtos;
}
