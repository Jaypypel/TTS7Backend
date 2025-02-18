package com.TTS.DbWebAPIs.DTO;


import com.TTS.DbWebAPIs.Entity.Activity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityDto {
    private String id;
    private String name;


    public static ActivityDto mapActivityToActivityDto(Activity activity){
        ActivityDto activityDto = new ActivityDto();
        activityDto.setId(String.valueOf(activity.getId()));
        activityDto.setName(activity.getName());
        return activityDto;
    }

}
