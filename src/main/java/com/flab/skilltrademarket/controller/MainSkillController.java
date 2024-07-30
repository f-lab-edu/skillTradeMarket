package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.common.CommonResponse;
import com.flab.skilltrademarket.domain.skill.dto.request.MainSkillCreateRequest;
import com.flab.skilltrademarket.domain.skill.dto.request.MainSkillUpdateRequest;
import com.flab.skilltrademarket.domain.skill.dto.response.MainSkillListResponse;
import com.flab.skilltrademarket.domain.skill.dto.response.MainSkillResponse;
import com.flab.skilltrademarket.service.MainSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MainSkillController {

    private final MainSkillService mainSkillService;

    @PostMapping("/stm/create")
    public void create(@RequestBody MainSkillCreateRequest createRequest) {
        mainSkillService.create(createRequest);
    }

    @GetMapping("/stm/{id}")
    CommonResponse<MainSkillResponse> findOne(@PathVariable("id") Long id) {
        MainSkillResponse skillResponse = mainSkillService.findOneById(id);
        return CommonResponse.success(skillResponse);
    }

    @GetMapping("/stm")
    CommonResponse<MainSkillListResponse> findAll() {
        return CommonResponse.success(mainSkillService.findAll());
    }

    @PatchMapping("/stm/{id}")
    CommonResponse<MainSkillResponse> update(@PathVariable("id") Long id, @RequestBody MainSkillUpdateRequest updateRequest) {
        return CommonResponse.success(mainSkillService.update(id, updateRequest));
    }

    @DeleteMapping("/stm/{id}")
    public void delete(@PathVariable("id") Long id) {
        mainSkillService.delete(id);
    }
}
