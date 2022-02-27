package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.service.impl.ActivityServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.alkemy.ong.controller.ControllerConstants.REQ_MAPP_ACTIVITIES;

@RestController
@RequiredArgsConstructor
@RequestMapping(REQ_MAPP_ACTIVITIES)
public class ActivityController {

    @Autowired
    private ActivityServiceImp activityServiceImp;

    @PostMapping()
    public ResponseEntity<ActivityDto> save(@Valid @RequestBody ActivityDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(activityServiceImp.save(dto));
    }

}
