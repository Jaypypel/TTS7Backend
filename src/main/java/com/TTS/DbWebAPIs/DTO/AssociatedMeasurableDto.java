package com.TTS.DbWebAPIs.DTO;

import lombok.Data;

@Data
public class AssociatedMeasurableDto {
      public Long id;
      public String name;
      public Long measurableQty;
      public String measurableUnit;
}
