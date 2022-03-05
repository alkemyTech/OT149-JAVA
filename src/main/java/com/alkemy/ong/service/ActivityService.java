package com.alkemy.ong.service;

import com.alkemy.ong.dto.ActivityDto;

public interface ActivityService {

    Long saveActivity(ActivityDto dto);

    ActivityDto updateActivity(Long id, ActivityDto dto);
}
