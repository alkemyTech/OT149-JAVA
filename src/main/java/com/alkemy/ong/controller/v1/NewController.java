package com.alkemy.ong.controller.v1;

import com.alkemy.ong.dto.NewDetailDto;
import com.alkemy.ong.service.NewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.alkemy.ong.controller.ControllerConstants.V_1_GETNEW;
import static com.alkemy.ong.controller.ControllerConstants.V_1_NEWS;

@RestController
@RequestMapping(V_1_NEWS)
@RequiredArgsConstructor
public class NewController {
    private final NewService service;

    @GetMapping(V_1_GETNEW)
    public ResponseEntity<NewDetailDto>getNewById(@PathVariable Long id){
        NewDetailDto detailNew = service.getNewById(id);
        return ResponseEntity.ok().body(detailNew);
    }
}
