package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.model.Activity;
import org.mapstruct.Mapper;

@Mapper
public interface ActivityMapper {
    Activity toActivity(ActivityDto dto);

    ActivityDto toDto(Activity activity);
}
