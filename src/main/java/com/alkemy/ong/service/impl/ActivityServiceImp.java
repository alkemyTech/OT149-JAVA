package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.exception.ActivityNotFoundException;
import com.alkemy.ong.mapper.ActivityMapper;
import com.alkemy.ong.model.Activity;
import com.alkemy.ong.repository.ActivitiesRepository;
import com.alkemy.ong.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ActivityServiceImp implements ActivityService {

    @Autowired
    private ActivitiesRepository activitiesRepository;
    @Autowired
    private ActivityMapper activityMapper;

    @Transactional
    @Override
    public Long saveActivity(ActivityDto dto) {

        Activity activity = activityMapper.toActivity(dto);

        activitiesRepository.save(activity);

        return activity.getId();
    }

    @Override
    public ActivityDto updateActivity(Long id, ActivityDto dto) {

        ActivityDto responseDto = activitiesRepository.findById(id).map(activity -> {

            activity.setName(dto.getName());
            activity.setText(dto.getText());
            activity.setImage(dto.getImage());

            return activityMapper.toDto(activitiesRepository.save(activity));
        }).orElseThrow(() -> {
            throw new ActivityNotFoundException();
        });

        return responseDto;
    }

}
