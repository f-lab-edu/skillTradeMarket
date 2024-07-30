package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.domain.skill.dto.request.SubSkillCreateRequest;
import com.flab.skilltrademarket.service.SubSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubSkillController {

    private final SubSkillService subSkillService;

    @PostMapping("/stm/create/sub")
    public void create(@RequestBody SubSkillCreateRequest createRequest) {
        subSkillService.create(createRequest);
    }
}
